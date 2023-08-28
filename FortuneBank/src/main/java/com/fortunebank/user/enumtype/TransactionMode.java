package com.fortunebank.user.enumtype;

public enum TransactionMode {
    NEFT("NEFT"),
    IMPS("IMPS"),
    RTGS("RTGS"),
    SELF("SELF");

    private final String displayName;

    /**
     * Constructs a TransactionMode enum value with a corresponding display name.
     *
     * @param displayName The display name associated with the transaction mode.
     */
    TransactionMode(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the transaction mode.
     *
     * @return The display name of the transaction mode.
     */
    public String getDisplayName() {
        return displayName;
    }
}
