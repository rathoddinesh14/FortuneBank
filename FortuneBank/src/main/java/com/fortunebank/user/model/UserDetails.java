package com.fortunebank.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fortunebank.user.enumtype.AccountStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * Represents user account details in the system.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserDetails {

	/**
	 * The unique account number associated with the user.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	 * The phone number of the user (unique).
	 */
	@Column(unique = true)
	private String phone;

	/**
	 * The email address of the user (unique).
	 */
	@Column(unique = true)
	private String email;

	/**
	 * The Aadhar number of the user (unique).
	 */
	@Column(unique = true)
	private String aadharNumber;

	/**
	 * The date of birth of the user.
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	/**
	 * The type of account associated with the user.
	 */
	private int accountType;

	/**
	 * The current balance in the user's account.
	 */
	private double balance;

	/**
	 * The status of the user's account.
	 */
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
}
