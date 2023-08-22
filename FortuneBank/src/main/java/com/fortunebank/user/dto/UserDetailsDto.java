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
	private AddressDto taddress;

	// permanent address
	private AddressDto paddress;
}
