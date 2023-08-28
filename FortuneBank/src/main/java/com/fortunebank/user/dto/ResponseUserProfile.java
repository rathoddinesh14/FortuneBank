package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing user profile information in
 * response.
 */
@Getter
@Setter
public class ResponseUserProfile {
    /**
     * The account number associated with the user profile.
     */
    private Long accountNumber;

    /**
     * The first name of the user.
     */
    private String firstName;

    /**
     * The middle name of the user.
     */
    private String middleName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The father's name of the user.
     */
    private String fatherName;

    /**
     * The phone number of the user.
     */
    private String phone;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The Aadhar number of the user.
     */
    private String aadharNumber;

    /**
     * The date of birth of the user.
     */
    private String dob;

    /**
     * The account type of the user.
     */
    private int accountType;

    /**
     * The balance in the user's account.
     */
    private double balance;

    /**
     * The status of the user's account.
     */
    private String accountStatus;
}
