package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing user details information.
 */
@Getter
@Setter
public class UserDetailsDto {
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
	 * The temporary address of the user.
	 */
	private AddressDto taddress;

	/**
	 * The permanent address of the user.
	 */
	private AddressDto paddress;
}
