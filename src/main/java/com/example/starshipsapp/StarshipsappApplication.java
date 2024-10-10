package com.example.starshipsapp;

import java.util.List;

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

	@Bean
	@Profile("!test")
	public CommandLineRunner run(StarshipService starshipService) throws Exception {
		return args -> {
			List<Starship> starships = starshipService.getStarships();
			log.info(starships.toString());
		};
	}

}
