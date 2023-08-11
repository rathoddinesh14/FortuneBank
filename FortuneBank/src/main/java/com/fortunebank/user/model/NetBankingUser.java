package com.fortunebank.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class NetBankingUser {

	@Id
	private Long accountNumber;
	private String loginPassword;
	private String transactionPassword;
}
