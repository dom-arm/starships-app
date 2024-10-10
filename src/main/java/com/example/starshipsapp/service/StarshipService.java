package com.example.starshipsapp.service;

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
		Result result = restClient.get().uri("/starships").accept(MediaType.APPLICATION_JSON).retrieve()
				.body(Result.class);
		Starship[] starships = result.results();
		return Arrays.asList(starships);
	}

}
