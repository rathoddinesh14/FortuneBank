package com.fortunebank.user.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.UserLoginDto;
import com.fortunebank.user.exception.InvalidLoginException;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.service.NetBankingService;

@RestController
@RequestMapping(value = "/api")
public class UserLoginController {
	@Autowired
	private NetBankingService netBankingService;

	/*
	 * This method is used to login the user
	 * 
	 * @param UserLoginDto uld
	 * 
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@Validated @RequestBody UserLoginDto uld) {
		String userid = uld.getUserid();
		String password = uld.getPassword();
		String message = "";

		try {
			NetBankingUser nbu = netBankingService.loginGetUser(userid);
			if (password.equals(nbu.getLoginPassword())) {
				message = "Login Successful";
				return ResponseEntity.ok(message);
			} else {
				message = "Invalid Password";
			}
		} catch (InvalidLoginException e) {
			message = e.getMessage();
			Logger.getLogger(UserLoginController.class.getName()).log(Level.SEVERE, e.getMessage());
		}
		return ResponseEntity.badRequest().body(message);
	}
}
