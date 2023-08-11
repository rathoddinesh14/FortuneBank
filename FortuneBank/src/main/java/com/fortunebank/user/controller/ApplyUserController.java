package com.fortunebank.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.UserDetailsDto;

@RestController
public class ApplyUserController {
	
	@PostMapping("/apply")
	public String applyUser(@RequestBody UserDetailsDto user_dto) {
		return "Success";
	}
}
