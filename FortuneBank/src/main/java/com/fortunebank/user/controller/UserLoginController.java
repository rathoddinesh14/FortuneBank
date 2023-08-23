package com.fortunebank.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.UserLoginDto;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.service.NetBankingService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public class UserLoginController {
	@Autowired
	private NetBankingService netBankingService;

	@PostMapping("/login")
	public boolean loginUser(@Validated @RequestBody UserLoginDto uld) throws Exception {
		String userid = uld.getUserid();
		String password = uld.getPassword();

		NetBankingUser nbu = netBankingService.loginGetUser(userid).orElseThrow(() -> new Exception("Error"));
		if (password.equals(nbu.getLoginPassword())) {
			return true;
		} else {
			return false;
		}
	}
}
