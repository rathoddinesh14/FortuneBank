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

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aid;

	// type of address
	private String type;

	private String line1;
	private String line2;
	private String landmark;
	private String state;
	private String city;
	private int pincode;

	@ManyToOne
	@JoinColumn(name = "accountnumber")
	private UserDetails ud;
}
