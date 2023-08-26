package com.fortunebank.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fortunebank.user.dto.UserLoginDto;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.service.NetBankingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
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
                .thenReturn(Optional.of(netBankingUser));

        boolean result = userLoginController.loginUser(userLoginDto);

        assertTrue(result);
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
                .thenReturn(Optional.of(netBankingUser));

        boolean result = userLoginController.loginUser(userLoginDto);

        assertFalse(result);
    }

    @Test
    public void testLoginUserUserNotFound() throws Exception {
        String userId = "nonExistentUserId";
        String password = "samplePassword";

        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setUserid(userId);
        userLoginDto.setPassword(password);

        when(netBankingService.loginGetUser(userId))
                .thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> userLoginController.loginUser(userLoginDto));
    }
}
