package com.fortunebank.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.fortunebank.user.dto.AddressDto;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.dto.UserDetailsDto;
import com.fortunebank.user.enumtype.AccountStatus;
import com.fortunebank.user.model.Address;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.AddressRepository;
import com.fortunebank.user.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUserDetails() {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setFirstName("John");
        userDetailsDto.setMiddleName("Doe");
        userDetailsDto.setLastName("Smith");
        userDetailsDto.setFatherName("James");
        userDetailsDto.setPhone("1234567890");
        userDetailsDto.setEmail("john@example.com");
        userDetailsDto.setAadharNumber("123456789012");
        userDetailsDto.setDob("2000-01-01");

        UserDetails user = new UserDetails();
        user.setAccountNumber(1L);
        user.setFirstName("John");

        when(userRepository.save(any(UserDetails.class))).thenReturn(user);

        UserDetails savedUser = userService.saveUserDetails(userDetailsDto);

        assertEquals("John", savedUser.getFirstName());
        assertEquals(1L, savedUser.getAccountNumber());
    }

    @Test
    public void testSaveAddress() {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setTaddress(new AddressDto());
        userDetailsDto.setPaddress(new AddressDto());

        UserDetails appliedUser = new UserDetails();
        appliedUser.setAccountNumber(1L);

        when(addressRepository.save(any(Address.class))).thenReturn(new Address());

        userService.saveAddress(userDetailsDto, appliedUser);

        verify(addressRepository, times(2)).save(any(Address.class));
    }

    @Test
    public void testGetUserProfile() {
        Long accountNumber = 1L;
        UserDetails userDetails = new UserDetails();
        userDetails.setAccountNumber(accountNumber);
        userDetails.setFirstName("John");
        userDetails.setDob(Date.valueOf("2000-01-01"));
        userDetails.setAccountStatus(AccountStatus.ENABLED);

        when(userRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(userDetails));

        ResponseUserProfile userProfile = userService.getUserProfile(accountNumber);

        assertEquals("John", userProfile.getFirstName());
        assertEquals(accountNumber, userProfile.getAccountNumber());
    }

    @Test
    public void testGetName() {
        Long accountNumber = 1L;
        UserDetails userDetails = new UserDetails();
        userDetails.setAccountNumber(accountNumber);
        userDetails.setFirstName("John");

        when(userRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(userDetails));

        String name = userService.getName(accountNumber);

        assertEquals("John", name);
        assertEquals(accountNumber, userDetails.getAccountNumber());
    }

    @Test
    public void testUpdateAccountStatus() {
        Long accountNumber = 1L;
        UserDetails userDetails = new UserDetails();
        userDetails.setAccountNumber(accountNumber);
        userDetails.setAccountStatus(AccountStatus.ENABLED);

        when(userRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(userDetails));

        boolean result = userService.updateAccountStatus(accountNumber, AccountStatus.DISABLED);

        assertTrue(result);
        assertEquals(AccountStatus.DISABLED, userDetails.getAccountStatus());
    }
}
