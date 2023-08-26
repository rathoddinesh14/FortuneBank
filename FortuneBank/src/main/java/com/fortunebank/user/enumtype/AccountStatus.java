package com.fortunebank.user.enumtype;

public enum AccountStatus {
    ENABLED("ENABLED"),
    DISABLED("DISABLED");

    private final String displayName;

    AccountStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
