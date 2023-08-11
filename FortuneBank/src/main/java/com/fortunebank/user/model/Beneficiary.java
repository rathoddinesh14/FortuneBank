package com.fortunebank.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bid;
	
	@ManyToOne
	@JoinColumn(name = "account_number")
	private UserDetails ud;
	
	@OneToOne
	@JoinColumn(name = "payee_account_number")
	private UserDetails payeeDetails;
	
	private String nickName;
}
