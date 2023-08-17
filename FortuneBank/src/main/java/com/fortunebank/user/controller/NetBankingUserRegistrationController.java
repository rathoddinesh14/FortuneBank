package com.fortunebank.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.ForgotPasswordDto;
import com.fortunebank.user.dto.ForgotUserIdDto;
import com.fortunebank.user.dto.UserRegistrationDto;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.service.NetBankingUserRegistrationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class NetBankingUserRegistrationController {

	@Autowired
	private NetBankingUserRegistrationService netBankingUserRegistrationService;

	@PostMapping("/register")
	public NetBankingUser register(@RequestBody UserRegistrationDto userRegDto) {
		NetBankingUser netBankingUser = new NetBankingUser();
		netBankingUser.setAccountNumber(userRegDto.getAccountnumber());
		netBankingUser.setLoginPassword(userRegDto.getLoginpassword());
		netBankingUser.setTransactionPassword(userRegDto.getTransactionpassword());
		netBankingUser.setUserId("" + userRegDto.getAccountnumber());
		return netBankingUserRegistrationService.registerNetBankingUser(netBankingUser);
	}

	@PostMapping("/resetpassword")
	public boolean resetPassword(@Validated @RequestBody ForgotPasswordDto fpd) {
		boolean res = false;
		try {
			res = netBankingUserRegistrationService.resetPassword(fpd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@PostMapping("/forgotuserid")
	public String forgotUserId(@Validated @RequestBody ForgotUserIdDto fud) {
		String res = "";
		try {
			res = netBankingUserRegistrationService.getUserId(fud);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
