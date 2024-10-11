package com.example.starshipsapp.exception;

public class InvalidQueryException extends RuntimeException {

	public InvalidQueryException(String sort, String order) {
		super("An invalid query value: \"" + sort + "\" or \"" + order + "\"");
	}
}
