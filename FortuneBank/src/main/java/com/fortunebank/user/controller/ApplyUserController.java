package com.fortunebank.user.controller;

import java.sql.Date;

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
import com.fortunebank.user.model.Address;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ApplyUserController {

	@Autowired
	private UserService userService;

	@PostMapping("/apply")
	public ResponseEntity<ResponseApplyUser> applyUser(@RequestBody UserDetailsDto user_dto) {
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
		UserDetails appliedUser = userService.saveUserDetails(user);

		Address tAddress = new Address();
		tAddress.setLine1(user_dto.getTaddress().getLine1());
		tAddress.setLine2(user_dto.getTaddress().getLine2());
		tAddress.setState(user_dto.getTaddress().getState());
		tAddress.setPincode(user_dto.getTaddress().getPincode());
		tAddress.setLandmark(user_dto.getTaddress().getLandmark());
		tAddress.setCity(user_dto.getTaddress().getCity());
		tAddress.setUd(appliedUser);
		tAddress.setType(user_dto.getTaddress().getType());
		userService.saveAddress(tAddress);

		Address pAddress = new Address();
		pAddress.setLine1(user_dto.getPaddress().getLine1());
		pAddress.setLine2(user_dto.getPaddress().getLine2());
		pAddress.setState(user_dto.getPaddress().getState());
		pAddress.setPincode(user_dto.getPaddress().getPincode());
		pAddress.setLandmark(user_dto.getPaddress().getLandmark());
		pAddress.setCity(user_dto.getPaddress().getCity());
		pAddress.setUd(appliedUser);
		pAddress.setType(user_dto.getPaddress().getType());
		userService.saveAddress(pAddress);

		ResponseApplyUser response = new ResponseApplyUser();
		response.setMessage("User applied successfully");
		response.setAccountNumber(appliedUser.getAccountNumber());
		return ResponseEntity.ok().body(response);
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
