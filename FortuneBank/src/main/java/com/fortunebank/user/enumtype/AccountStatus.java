package com.fortunebank.user.enumtype;

/**
 * Enum representing different account status types.
 */
public enum AccountStatus {
    ENABLED("ENABLED"),
    DISABLED("DISABLED");

    private final String displayName;

    /**
     * Constructs an AccountStatus enum value with a corresponding display name.
     *
     * @param displayName The display name associated with the account status.
     */
    AccountStatus(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the account status.
     *
     * @return The display name of the account status.
     */
    public String getDisplayName() {
        return displayName;
    }
}
