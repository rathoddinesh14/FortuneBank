package com.fortunebank.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fortunebank.user.dto.AddressDto;
import com.fortunebank.user.dto.ResponseApplyUser;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.dto.UserDetailsDto;
import com.fortunebank.user.enumtype.AccountStatus;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ApplyUserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private ApplyUserController applyUserController;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testApplyUser() {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setFirstName("John");
        userDetailsDto.setMiddleName("Doe");
        userDetailsDto.setLastName("Smith");
        userDetailsDto.setFatherName("Michael");
        userDetailsDto.setPhone("1234567890");
        userDetailsDto.setEmail("john.doe@example.com");
        userDetailsDto.setAadharNumber("123456789012");
        userDetailsDto.setDob("1990-01-01");

        AddressDto tAddressDto = new AddressDto();
        tAddressDto.setLine1("123 Street");
        tAddressDto.setLine2("Apartment 4A");
        tAddressDto.setCity("City");
        tAddressDto.setState("State");
        tAddressDto.setPincode(12345);
        tAddressDto.setLandmark("Landmark");
        tAddressDto.setType("Temporary");

        AddressDto pAddressDto = new AddressDto();
        pAddressDto.setLine1("456 Avenue");
        pAddressDto.setLine2("Unit 7B");
        pAddressDto.setCity("City");
        pAddressDto.setState("State");
        pAddressDto.setPincode(67890);
        pAddressDto.setLandmark("Near Park");
        pAddressDto.setType("Permanent");

        userDetailsDto.setTaddress(tAddressDto);
        userDetailsDto.setPaddress(pAddressDto);

        UserDetails appliedUser = new UserDetails();
        appliedUser.setAccountNumber(123456789L);
        appliedUser.setFirstName("John");
        appliedUser.setMiddleName("Doe");
        appliedUser.setLastName("Smith");
        appliedUser.setFatherName("Michael");
        appliedUser.setPhone("1234567890");
        appliedUser.setEmail("john.doe@example.com");
        appliedUser.setAadharNumber("123456789012");
        appliedUser.setDob(java.sql.Date.valueOf("1990-01-01"));
        appliedUser.setAccountType(0);
        appliedUser.setBalance(0);
        appliedUser.setAccountStatus(AccountStatus.ENABLED);

        when(userService.saveUserDetails(any(UserDetailsDto.class))).thenReturn(appliedUser);

        ResponseEntity<ResponseApplyUser> responseEntity = applyUserController.applyUser(userDetailsDto);
        ResponseApplyUser response = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        if (response == null) {
            fail("Response body is null");
        } else {
            assertEquals("User applied successfully", response.getMessage());
            assertEquals(appliedUser.getAccountNumber(), response.getAccountNumber());
        }
    }

    @Test
    public void testGetUserProfile() {
        Long accountNumber = 123456789L;
        ResponseUserProfile userProfile = new ResponseUserProfile();

        when(userService.getUserProfile(accountNumber)).thenReturn(userProfile);

        ResponseEntity<ResponseUserProfile> responseEntity = applyUserController.getUserProfile(accountNumber);
        ResponseUserProfile response = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userProfile, response);
    }

    @Test
    public void testGetName() {

        Long accountNumber = 123456789L;
        String expectedName = "John Doe";

        when(userService.getName(accountNumber))
                .thenReturn(expectedName);

        ResponseEntity<String> responseEntity = applyUserController.getName(accountNumber);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(expectedName, responseEntity.getBody());

        verify(userService).getName(accountNumber);
    }
}
