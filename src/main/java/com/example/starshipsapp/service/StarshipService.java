package com.example.starshipsapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.starshipsapp.Result;
import com.example.starshipsapp.Starship;

@Service
public class StarshipService {

	private final RestClient restClient;

	public StarshipService(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.baseUrl("https://swapi.dev/api").build();
	}

	public List<Starship> getStarships() {
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

}
