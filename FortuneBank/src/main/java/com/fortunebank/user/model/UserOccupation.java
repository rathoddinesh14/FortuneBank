package com.fortunebank.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserOccupation {
	
	@Id
	@OneToOne
	@JoinColumn(name = "account_number")
	private UserDetails ud;
	private int type;
	private int incomeSource;
	private double grossAnnualIncome;
}
