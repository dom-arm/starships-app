package com.example.starshipsapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import com.example.starshipsapp.repository.StarshipRepository;

@Configuration
public class LoadData {

	private static final Logger log = LoggerFactory.getLogger(LoadData.class);

	@Bean
	public RestClient restClient(RestClient.Builder restClientBuilder) {
		return restClientBuilder.baseUrl("https://swapi.dev/api").build();
	}

	@Bean
	public List<Starship> data(RestClient restClient) {
		// The response may be paginated so I have to check for the next hyperlink

		String uri = "/starships";
		List<Starship> starships = new ArrayList<>();

		while (uri != null) {
			Result result = restClient.get().uri(uri).accept(MediaType.APPLICATION_JSON).retrieve().body(Result.class);
			Starship[] starshipsOnCurrentPage = result.results();

			starships.addAll(Arrays.asList(starshipsOnCurrentPage));
			uri = result.next();
		}

		// TODO can compare the count field of the Result with the length of starships
		// to confirm all starships is fetched

		return starships;
	}

	@Bean
	public CommandLineRunner initData(List<Starship> data, StarshipRepository repository) throws Exception {
		data.forEach(record -> repository.save(record));
		return args -> {
			log.info("Data has been loaded to repository");
		};

	}
}
