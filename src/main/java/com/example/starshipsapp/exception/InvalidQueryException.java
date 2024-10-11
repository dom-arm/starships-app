package com.example.starshipsapp.exception;

public class InvalidQueryException extends RuntimeException {

	private static final long serialVersionUID = 8636315545642109845L; // Bc it's a Serializable

	public InvalidQueryException(String sort, String order) {
		super("An invalid query value: \"" + sort + "\" or \"" + order + "\"");
	}
}
