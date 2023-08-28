package com.fortunebank.user.enumtype;

/**
 * Enum representing different types of banking transactions.
 */
public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal"),
    TRANSFER("Transfer");

    private final String displayName;

    /**
     * Constructs a TransactionType enum value with a corresponding display name.
     *
     * @param displayName The display name associated with the transaction type.
     */
    TransactionType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the transaction type.
     *
     * @return The display name of the transaction type.
     */
    public String getDisplayName() {
        return displayName;
    }
}
