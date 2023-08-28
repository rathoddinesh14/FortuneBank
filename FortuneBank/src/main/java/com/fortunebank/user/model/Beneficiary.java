package com.fortunebank.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a beneficiary associated with a user account.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Beneficiary {

	/**
	 * The unique identifier for the beneficiary.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bid;

	/**
	 * The user details of the account holder who owns this beneficiary.
	 */
	@ManyToOne
	@JoinColumn(name = "account_number")
	private UserDetails ud;

	/**
	 * The user details of the beneficiary.
	 */
	@ManyToOne
	@JoinColumn(name = "payee_account_number")
	private UserDetails payeeDetails;

	/**
	 * The name of the beneficiary.
	 */
	private String name;

	/**
	 * The nickname assigned to the beneficiary.
	 */
	private String nickName;
}
