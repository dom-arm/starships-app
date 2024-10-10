package com.example.starshipsapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Starship(String name, String cost_in_credits) {
}
