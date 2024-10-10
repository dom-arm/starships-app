package com.example.starshipsapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Result(int count, String next, String previous, Starship[] results) {
}
