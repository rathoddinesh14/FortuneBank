package com.fortunebank.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fortunebank.user.dto.ForgotPasswordDto;
import com.fortunebank.user.dto.ForgotUserIdDto;
import com.fortunebank.user.dto.UserRegistrationDto;
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
public class NetBankingControllerTest {

    @MockBean
    private NetBankingService netBankingService;

    @Autowired
    private NetBankingController netBankingController;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testRegister() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        NetBankingUser netBankingUser = new NetBankingUser();

        when(netBankingService.registerNetBankingUser(any(NetBankingUser.class)))
                .thenReturn(netBankingUser);

        NetBankingUser result = netBankingController.register(userRegistrationDto);

        assertEquals(netBankingUser, result);
    }

    @Test
    public void testResetPassword() {
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();

        try {
            when(netBankingService.resetPassword(any(ForgotPasswordDto.class)))
                    .thenReturn(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean result = netBankingController.resetPassword(forgotPasswordDto);

        assertTrue(result);
    }

    @Test
    public void testForgotUserId() {
        ForgotUserIdDto forgotUserIdDto = new ForgotUserIdDto();

        try {
            when(netBankingService.getUserId(any(ForgotUserIdDto.class)))
                    .thenReturn("sampleUserId");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = netBankingController.forgotUserId(forgotUserIdDto);

        assertEquals("sampleUserId", result);
    }

    @Test
    public void testGetAccountNumber() throws Exception {
        Long userId = 123456L;

        when(netBankingService.getAccountNumber(userId))
                .thenReturn(987654321L);

        ResponseEntity<Long> responseEntity = netBankingController.getAccountNumber(userId);
        Long result = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(987654321L, result);
    }
}
