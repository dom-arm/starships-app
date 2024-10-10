package com.example.starshipsapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.starshipsapp.Starship;

@Repository
public class StarshipRepository {

	private List<Starship> database = new ArrayList<Starship>();

	public void save(Starship starship) {
		this.database.add(starship);
	}

}
