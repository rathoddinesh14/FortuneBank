package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing the response after applying for
 * a user account.
 */
@Getter
@Setter
public class ResponseApplyUser {
    /**
     * Message indicating the status of the user application.
     */
    private String message;

    /**
     * The account number assigned to the user after successful application.
     */
    private Long accountNumber;
}
