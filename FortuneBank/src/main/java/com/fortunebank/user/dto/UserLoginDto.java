package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing user login information.
 */
@Getter
@Setter
public class UserLoginDto {
	/**
	 * The user ID for authentication.
	 */
	private String userid;

	/**
	 * The password for authentication.
	 */
	private String password;
}
