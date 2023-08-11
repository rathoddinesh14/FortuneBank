package com.fortunebank.user.dto;

public class UserRegistrationDto {
	
	private String accountnumber;
	private String loginpassword;
	private String transactionpassword;
	private String otp;
	
	public UserRegistrationDto(String accountnumber, String loginpassword, String transactionpassword, String otp) {
		this.accountnumber = accountnumber;
		this.loginpassword = loginpassword;
		this.transactionpassword = transactionpassword;
		this.otp = otp;
	}

	public UserRegistrationDto() {
	}
	
	
}
