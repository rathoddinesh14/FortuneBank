package com.fortunebank.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fortunebank.user.enumtype.TransactionMode;
import com.fortunebank.user.enumtype.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Represents a financial transaction made by a user.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

	/**
	 * The unique identifier for the transaction.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tid;

	/**
	 * The user details of the account from which the transaction was initiated.
	 */
	@ManyToOne
	@JoinColumn(name = "from_account_number")
	private UserDetails fud;

	/**
	 * The user details of the account to which the transaction was made.
	 */
	@ManyToOne
	@JoinColumn(name = "to_account_number")
	private UserDetails tud;

	/**
	 * The amount of the transaction.
	 */
	private double amount;

	/**
	 * The date and time of the transaction.
	 */
	@JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss")
	private Date date;

	/**
	 * A remark or description for the transaction.
	 */
	private String remark;

	/**
	 * Instructions related to the maturity of the transaction, if applicable.
	 */
	private String maturityInstructions;

	/**
	 * The type of the transaction.
	 */
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	/**
	 * The mode of the transaction.
	 */
	@Enumerated(EnumType.STRING)
	private TransactionMode transactionMode;
}
