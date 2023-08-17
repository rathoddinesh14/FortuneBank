package com.fortunebank.user.controller;

import java.sql.Date;
import java.text.DateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.UserDetailsDto;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.ApplyUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ApplyUserController {

	@Autowired
	private ApplyUserService applyUserService;

	@PostMapping("/apply")
	public UserDetails applyUser(@RequestBody UserDetailsDto user_dto) {
		UserDetails user = new UserDetails();
		user.setFirstName(user_dto.getFirstName());
		user.setMiddleName(user_dto.getMiddleName());
		user.setLastName(user_dto.getLastName());
		user.setFatherName(user_dto.getFatherName());
		user.setPhone(user_dto.getPhone());
		user.setEmail(user_dto.getEmail());
		user.setAadharNumber(user_dto.getAadharNumber());
		Date dob = Date.valueOf(user_dto.getDob());
		user.setDob(dob);
		user.setAccountType(0);
		user.setBalance(0);

		return applyUserService.saveUserDetails(user);
	}
}
