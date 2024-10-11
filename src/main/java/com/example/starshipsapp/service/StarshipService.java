package com.example.starshipsapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.starshipsapp.Starship;

@Service
public class StarshipService {

	private final List<Starship> data;

	public StarshipService(ExternalDataService externalDataService) {
		this.data = externalDataService.fetchData();
	}

	public List<Starship> getData() {
		return this.data;
	}

	public List<Starship> sortBy(String sort, String order) {

		switch (sort) {
		case "price":
			List<Starship> sorted = sortByPrice(order);
			return sorted.size() > 10 ? sorted.subList(0, 10) : sorted;
		default:
			// Throw not sorting on this yet
			return null;
		}

	}

	private List<Starship> sortByPrice(String order) {

		switch (order) {
		case "desc":
			return sortInDescendingOrder(this.getData());
		case "asc":
			// Nothing implemented yet
			return null;
		default:
			// Not ordering on this
			return null;
		}

	}

	private List<Starship> sortInDescendingOrder(List<Starship> data) {

		data.sort((first, second) -> {
			String price1 = first.cost_in_credits();
			String price2 = second.cost_in_credits();

			if (price1.equals("unknown"))
				return 1;
			if (price2.equals("unknown"))
				return -1;

			// desc
			return Long.compare(Long.parseLong(price2), Long.parseLong(price1));
		});

		return data;
	}
}
