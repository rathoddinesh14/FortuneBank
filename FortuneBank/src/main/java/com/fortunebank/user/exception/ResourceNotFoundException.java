package com.fortunebank.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to represent the scenario where a requested resource
 * is not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of ResourceNotFoundException with a custom error
	 * message.
	 *
	 * @param message The custom error message.
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
