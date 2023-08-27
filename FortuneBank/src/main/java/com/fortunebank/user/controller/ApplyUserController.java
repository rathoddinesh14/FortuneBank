package com.fortunebank.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.ResponseApplyUser;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.dto.UserDetailsDto;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ApplyUserController {

	@Autowired
	private UserService userService;

	@PostMapping("/apply")
	public ResponseEntity<ResponseApplyUser> applyUser(@RequestBody UserDetailsDto userDto) {
		try {
			UserDetails appliedUser = userService.saveUserDetails(userDto);
			userService.saveAddress(userDto, appliedUser);

			ResponseApplyUser response = new ResponseApplyUser();
			response.setMessage("User applied successfully");
			response.setAccountNumber(appliedUser.getAccountNumber());
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseApplyUser response = new ResponseApplyUser();
			response.setMessage(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

	}

	@GetMapping("/userprofile/{accountNumber}")
	public ResponseEntity<ResponseUserProfile> getUserProfile(@PathVariable Long accountNumber) {
		return new ResponseEntity<ResponseUserProfile>(userService.getUserProfile(accountNumber), HttpStatus.OK);
	}

	@GetMapping("/getname/{accountNumber}")
	public ResponseEntity<String> getName(@PathVariable Long accountNumber) {
		return new ResponseEntity<String>(userService.getName(accountNumber), HttpStatus.OK);
	}

}
