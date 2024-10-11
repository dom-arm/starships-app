package com.example.starshipsapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.starshipsapp.service.ExternalDataService;

@SpringBootApplication
public class StarshipsappApplication {

	private static final Logger log = LoggerFactory.getLogger(StarshipsappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StarshipsappApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(ExternalDataService externalDataService) throws Exception {
		return args -> {
			log.info("Up and running");
			log.info(externalDataService.fetchData().toString());
		};

	}

}
