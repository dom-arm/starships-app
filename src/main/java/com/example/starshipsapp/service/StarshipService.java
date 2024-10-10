package com.example.starshipsapp.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.starshipsapp.Starship;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StarshipService {

	private final RestClient restClient;

	public StarshipService(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.baseUrl("https://swapi.dev/api").build();
	}

	public Starship[] getStarships() {
		Starship[] starships = restClient.get().uri("/starships").accept(MediaType.APPLICATION_JSON)
				.exchange(StarshipService::exchangeToStarshipArray);
		return starships;
	}

	private static Starship[] exchangeToStarshipArray(HttpRequest request,
			RestClient.RequestHeadersSpec.ConvertibleClientHttpResponse response) throws IOException {
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			try {
				InputStream body = response.getBody();
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.readValue(body, Starship[].class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
