package com.fortunebank.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents user occupation details in the system.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserOccupation {

	/**
	 * The user account details associated with this occupation.
	 */
	@Id
	@OneToOne
	@JoinColumn(name = "account_number")
	private UserDetails ud;

	/**
	 * The type of user's occupation.
	 */
	private int type;

	/**
	 * The source of income for the user's occupation.
	 */
	private int incomeSource;

	/**
	 * The gross annual income of the user.
	 */
	private double grossAnnualIncome;
}
