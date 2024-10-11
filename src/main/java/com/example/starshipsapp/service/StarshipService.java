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

	public List<Starship> sortBy(String sort, String order) throws Exception {

		switch (sort) {
		case "price":

			List<Starship> sorted = sortByPrice(order);

			// The top 10 starships in the sorted list should be returned
			return sorted.size() > 10 ? sorted.subList(0, 10) : sorted;

		default:
			throw new Exception("Sorting on " + sort + " parameter not yet implemented");
		}

	}

	private List<Starship> sortByPrice(String order) throws Exception {

		switch (order) {
		case "desc":
			return sortInDescendingOrder(this.getData());
		default:
			throw new Exception("Ordering on " + order + " parameter not yet implemented");
		}

	}

	private List<Starship> sortInDescendingOrder(List<Starship> data) {

		data.sort((first, second) -> {
			String price1 = first.cost_in_credits();
			String price2 = second.cost_in_credits();

			if (price1.equals("unknown"))
				return 1; // Move first after second
			if (price2.equals("unknown"))
				return -1; // Keep second after first

			// Descending
			return Long.compare(Long.parseLong(price2), Long.parseLong(price1));
		});

		return data;
	}
}
