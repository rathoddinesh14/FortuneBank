package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationDto {
	
	private String accountnumber;
	private String loginpassword;
	private String transactionpassword;
	private String otp;
	
}
