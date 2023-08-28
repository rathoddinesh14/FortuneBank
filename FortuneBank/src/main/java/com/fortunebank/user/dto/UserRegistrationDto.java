package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing user registration information.
 */
@Getter
@Setter
public class UserRegistrationDto {
	/**
	 * The account number of the user.
	 */
	private Long accountnumber;

	/**
	 * The login password chosen by the user.
	 */
	private String loginpassword;

	/**
	 * The transaction password chosen by the user.
	 */
	private String transactionpassword;

	/**
	 * The one-time password (OTP) for verification.
	 */
	private String otp;
}
