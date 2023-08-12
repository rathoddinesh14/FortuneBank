package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
	private Long accountnumber;
	private String loginpassword;
	private String transactionpassword;
	private String otp;
}
