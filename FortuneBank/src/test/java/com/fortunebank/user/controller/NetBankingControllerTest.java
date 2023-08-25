package com.fortunebank.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fortunebank.user.dto.ForgotPasswordDto;
import com.fortunebank.user.dto.ForgotUserIdDto;
import com.fortunebank.user.dto.UserRegistrationDto;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.service.NetBankingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NetBankingControllerTest {

    @MockBean
    private NetBankingService netBankingService;

    @Autowired
    private NetBankingController netBankingController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        // Set properties for userRegistrationDto
        
        NetBankingUser netBankingUser = new NetBankingUser();
        // Set properties for netBankingUser
        
        when(netBankingService.registerNetBankingUser(any(NetBankingUser.class)))
            .thenReturn(netBankingUser);

        NetBankingUser result = netBankingController.register(userRegistrationDto);

        assertEquals(netBankingUser, result);
    }

    @Test
    public void testResetPassword() {
        ForgotPasswordDto forgotPasswordDto = new ForgotPasswordDto();
        // Set properties for forgotPasswordDto

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
        // Set properties for forgotUserIdDto

        try {
			when(netBankingService.getUserId(any(ForgotUserIdDto.class)))
			    .thenReturn("sampleUserId");
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
