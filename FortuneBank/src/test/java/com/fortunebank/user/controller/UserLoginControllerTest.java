package com.fortunebank.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




import static org.mockito.Mockito.*;

import com.fortunebank.user.dto.UserLoginDto;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.service.NetBankingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserLoginControllerTest {

    @Mock
    private NetBankingService netBankingService;

    @InjectMocks
    private UserLoginController userLoginController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
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

