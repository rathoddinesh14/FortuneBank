package com.fortunebank.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.UserRegistrationDto;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.service.NetBankingUserRegistrationService;

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
		return netBankingUserRegistrationService.registerNetBankingUser(netBankingUser);
	}
}
