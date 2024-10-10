package com.example.starshipsapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.example.starshipsapp.service.StarshipService;

@SpringBootApplication
public class StarshipsappApplication {

	private static final Logger log = LoggerFactory.getLogger(StarshipsappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StarshipsappApplication.class, args);
	}

//	@Bean
//	public RestClient restClient(RestClient.Builder restClientBuilder) {
//		return restClientBuilder.baseUrl("https://swapi.dev/api").build();
//	}

	@Bean
	@Profile("!test")
	public CommandLineRunner run(StarshipService starshipService) throws Exception {
		return args -> {
			Starship[] starships = starshipService.getStarships();
			log.info(starships.toString());
		};
	}
//	@Bean
//	@Profile("!test")
//	public CommandLineRunner run(RestClient restClient) throws Exception {
//		return args -> {
//			Starship starship = restClient.get().uri("/starships").retrieve().body(Starship.class);
//			log.info(starship.toString());
//		};
//	}

}
