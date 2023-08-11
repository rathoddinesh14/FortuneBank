package com.fortunebank.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.UserRegistrationDto;

@RestController
public class UserRegistrationController {

	@PostMapping("/register")
	public UserRegistrationDto register(@RequestBody UserRegistrationDto userRegDto) {
		return userRegDto;
	}
	
//	@GetMapping("/users")
//	public UserRegistrationDto getAllUsers() {
//		UserRegistrationDto urd = new UserRegistrationDto("","","","");
//		return urd;
//	}
	
}
