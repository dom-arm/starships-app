package com.example.starshipsapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.starshipsapp.Starship;
import com.example.starshipsapp.service.StarshipService;

@RestController
public class StarshipController {

	private final StarshipService starshipService;
//	private final ExternalDataService externalDataService;

	public StarshipController(StarshipService starshipService) {
		this.starshipService = starshipService;
//		this.externalDataService = externalDataService;
	}

	@GetMapping("/starships")
	public List<Starship> starships(@RequestParam("sort") String sort, @RequestParam("order") String order) {
		List<Starship> sorted = starshipService.sortBy(sort, order);
		return sorted;
	}
}
