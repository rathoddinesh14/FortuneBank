package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsDto {
	private String firstName;
	private String middleName;
	private String lastName;
	private String fatherName;
	private String phone;
	private String email;
	private String aadharNumber;
	private String dob;

	// temporary address
	private String line1;
	private String line2;
	private String landmark;
	private String state;
	private String city;
	private int pincode;

	// permanent address
	private String pline1;
	private String pline2;
	private String plandmark;
	private String pstate;
	private String pcity;
	private int ppincode;
}
