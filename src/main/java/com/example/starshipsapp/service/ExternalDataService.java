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
public class ExternalDataService {

	private final String BASE_URL = "https://swapi.dev/api";
	private final String ROOT_RESOURCE = "/starships";

	private final RestClient restClient;

	public ExternalDataService(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.baseUrl(BASE_URL).build();
	}

	public List<Starship> fetchData() {

		// The response may be paginated so I have to check for the next hyperlink
		String uri = ROOT_RESOURCE;
		List<Starship> data = new ArrayList<>();

		while (uri != null) {
			Result result = restClient.get().uri(uri).accept(MediaType.APPLICATION_JSON).retrieve().body(Result.class);
			Starship[] recordsOnCurrentPage = result.results();

			data.addAll(Arrays.asList(recordsOnCurrentPage));
			uri = result.next();
		}

		return data;

	}

}
