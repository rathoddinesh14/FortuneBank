package com.fortunebank.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fortunebank.user.dto.ResponseApplyUser;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.dto.UserDetailsDto;
import com.fortunebank.user.model.Address;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApplyUserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private ApplyUserController applyUserController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testApplyUser() {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        // Set properties for userDetailsDto
        
        UserDetails appliedUser = new UserDetails();
        // Set properties for appliedUser
        
        Address tAddress = new Address();
        // Set properties for tAddress

        Address pAddress = new Address();
        // Set properties for pAddress

        when(userService.saveUserDetails(any(UserDetails.class))).thenReturn(appliedUser);
        
        // Using Answer to handle different responses for saveAddress
        when(userService.saveAddress(any(Address.class)))
            .thenAnswer(invocation -> {
                if (invocation.getArguments()[0] == tAddress) {
                    return tAddress;
                } else if (invocation.getArguments()[0] == pAddress) {
                    return pAddress;
                }
                return null; // Handle other cases as needed
            });

        ResponseEntity<ResponseApplyUser> responseEntity = applyUserController.applyUser(userDetailsDto);
        ResponseApplyUser response = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User applied successfully", response.getMessage());
        assertEquals(appliedUser.getAccountNumber(), response.getAccountNumber());
    }


    @Test
    public void testGetUserProfile() {
        Long accountNumber = 123456789L;
        ResponseUserProfile userProfile = new ResponseUserProfile();
        // Set properties for userProfile
        
        when(userService.getUserProfile(accountNumber)).thenReturn(userProfile);

        ResponseEntity<ResponseUserProfile> responseEntity = applyUserController.getUserProfile(accountNumber);
        ResponseUserProfile response = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userProfile, response);
    }
}
