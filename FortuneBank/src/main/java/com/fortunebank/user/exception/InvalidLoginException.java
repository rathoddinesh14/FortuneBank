package com.fortunebank.user.exception;

/**
 * Custom exception class to represent the scenario where login credentials are
 * invalid.
 */
public class InvalidLoginException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of InvalidLoginException with a default error message.
     */
    public InvalidLoginException() {
        super("Invalid login credentials");
    }

    /**
     * Constructs an instance of InvalidLoginException with a custom error message.
     *
     * @param message The custom error message.
     */
    public InvalidLoginException(String message) {
        super(message);
    }
}
