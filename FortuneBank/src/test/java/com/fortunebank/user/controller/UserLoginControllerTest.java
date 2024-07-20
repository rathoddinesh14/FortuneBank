package com.fortunebank.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fortunebank.user.dto.UserLoginDto;
import com.fortunebank.user.exception.InvalidLoginException;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.service.NetBankingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserLoginControllerTest {

    @MockBean
    private NetBankingService netBankingService;

    @Autowired
    private UserLoginController userLoginController;

    @BeforeEach
    public void setup() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testLoginUserValidCredentials() throws Exception {
        String userId = "sampleUserId";
        String password = "samplePassword";

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setUserid(userId);
        userLoginDto.setPassword(password);

        NetBankingUser netBankingUser = new NetBankingUser();
        netBankingUser.setLoginPassword(password);

        when(netBankingService.loginGetUser(userId))
                .thenReturn(netBankingUser);

        ResponseEntity<String> result = userLoginController.loginUser(userLoginDto);
        assertEquals("Login Successful", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testLoginUserInvalidCredentials() throws Exception {
        String userId = "sampleUserId";
        String password = "samplePassword";

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setUserid(userId);
        userLoginDto.setPassword(password);

        NetBankingUser netBankingUser = new NetBankingUser();
        netBankingUser.setLoginPassword("incorrectPassword");

        when(netBankingService.loginGetUser(userId))
                .thenReturn(netBankingUser);

        ResponseEntity<String> result = userLoginController.loginUser(userLoginDto);
        assertEquals("Invalid Password", result.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void testLoginUserUserNotFound() throws Exception {
        String userId = "nonExistentUserId";
        String password = "samplePassword";

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setUserid(userId);
        userLoginDto.setPassword(password);

        when(netBankingService.loginGetUser(userId))
                .thenThrow(new InvalidLoginException("User with this userId not found!"));

        ResponseEntity<String> result = userLoginController.loginUser(userLoginDto);
        assertEquals("User with this userId not found!", result.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}
