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
 * Represents an address associated with a user account.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Address {

	/**
	 * The unique identifier for the address.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aid;

	/**
	 * The type of the address (e.g., "Temporary" or "Permanent").
	 */
	private String type;

	/**
	 * The first line of the address.
	 */
	private String line1;

	/**
	 * The second line of the address.
	 */
	private String line2;

	/**
	 * A landmark or reference point for the address.
	 */
	private String landmark;

	/**
	 * The state where the address is located.
	 */
	private String state;

	/**
	 * The city where the address is located.
	 */
	private String city;

	/**
	 * The pincode (postal code) for the address.
	 */
	private int pincode;

	/**
	 * The user details associated with this address.
	 */
	@ManyToOne
	@JoinColumn(name = "accountnumber")
	private UserDetails ud;
}
