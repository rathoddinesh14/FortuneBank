package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing the information required for
 * managing payees.
 */
@NoArgsConstructor
@Getter
@Setter
public class PayeeDto {
	/**
	 * The name of the beneficiary.
	 */
	private String beneficiaryname;

	/**
	 * The account number of the user.
	 */
	private Long accountnumber;

	/**
	 * The account number of the payee.
	 */
	private Long payeeaccountnumber;

	/**
	 * Flag indicating whether to save the beneficiary.
	 */
	private boolean savebeneficiary;

	/**
	 * A nickname for the payee.
	 */
	private String nickname;
}
