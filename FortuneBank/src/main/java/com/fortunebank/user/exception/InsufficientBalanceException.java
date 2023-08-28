package com.fortunebank.user.exception;

/**
 * Custom exception class to represent the scenario where the user has
 * insufficient balance for a transaction.
 */
public class InsufficientBalanceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance of InsufficientBalanceException with a default error
     * message.
     */
    public InsufficientBalanceException() {
        super("Insufficient balance");
    }

    /**
     * Constructs an instance of InsufficientBalanceException with a custom error
     * message.
     *
     * @param message The custom error message.
     */
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
