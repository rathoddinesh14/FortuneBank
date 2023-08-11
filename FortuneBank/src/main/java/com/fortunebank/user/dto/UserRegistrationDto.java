package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserRegistrationDto {
	
	private String accountnumber;
	private String loginpassword;
	private String transactionpassword;
	private String otp;
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getLoginpassword() {
		return loginpassword;
	}
	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}
	public String getTransactionpassword() {
		return transactionpassword;
	}
	public void setTransactionpassword(String transactionpassword) {
		this.transactionpassword = transactionpassword;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
}
