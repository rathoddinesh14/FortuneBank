package com.fortunebank.user.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tid;
	
	@OneToOne
	@JoinColumn(name = "from_account_number")
	private UserDetails fud;
	
	@OneToOne
	@JoinColumn(name = "to_account_number")
	private UserDetails tud;
	
	private double amount;
	
	@JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss")
	private Date date;
	
	private String remark;
	
	private String maturityInstructions;
}
