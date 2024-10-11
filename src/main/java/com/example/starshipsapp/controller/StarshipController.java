package com.example.starshipsapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.starshipsapp.Starship;
import com.example.starshipsapp.exception.InvalidQueryException;
import com.example.starshipsapp.service.StarshipService;

@RestController
public class StarshipController {

	private final StarshipService starshipService;

	public StarshipController(StarshipService starshipService) {
		this.starshipService = starshipService;
	}

	@GetMapping("/starships")
	public List<Starship> starships(@RequestParam("sort") String sort, @RequestParam("order") String order) {

		try {
			return starshipService.sortBy(sort, order);
		} catch (Exception e) {
			throw new InvalidQueryException(sort, order);
		}
	}
}
