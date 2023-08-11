package com.fortunebank.user.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNumber;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String fatherName;
	
	@Column(unique = true)
	private String phone;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String aadharNumber;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	private int accountType;
	private double balance;
}
