package com.fortunebank.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Net Banking user account.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class NetBankingUser {

	/**
	 * The account number associated with the Net Banking user account.
	 */
	@Id
	private Long accountNumber;

	/**
	 * The login password for the Net Banking user account.
	 */
	private String loginPassword;

	/**
	 * The transaction password for the Net Banking user account.
	 */
	private String transactionPassword;

	/**
	 * The unique user ID associated with the Net Banking user account.
	 */
	@Column(unique = true)
	private String userId;
}
