package com.fortunebank.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.fortunebank.user.dto.ForgotPasswordDto;
import com.fortunebank.user.dto.ForgotUserIdDto;
import com.fortunebank.user.exception.InvalidLoginException;
import com.fortunebank.user.exception.ResourceNotFoundException;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.repository.NetBankingUserRepository;

@SpringBootTest
@ActiveProfiles("test")
public class NetBankingServiceTest {

    @MockBean
    private NetBankingUserRepository netBankingUserRepository;

    @Autowired
    private NetBankingService netBankingService;

    @Test
    public void testGetAccountNumber() throws Exception {
        Long userId = 1L;
        NetBankingUser user = new NetBankingUser();
        user.setAccountNumber(1234567890L);

        when(netBankingUserRepository.findById(userId)).thenReturn(Optional.of(user));

        Long accountNumber = netBankingService.getAccountNumber(userId);

        assertEquals(1234567890L, accountNumber);
    }

    @Test
    public void testGetUserId() throws Exception {
        ForgotUserIdDto fud = new ForgotUserIdDto();
        fud.setAccountnumber(1234567890L);
        NetBankingUser user = new NetBankingUser();
        user.setUserId("testuser");

        when(netBankingUserRepository.findByAccountNumber(fud.getAccountnumber())).thenReturn(Optional.of(user));

        String userId = netBankingService.getUserId(fud);

        assertEquals("testuser", userId);
    }

    @Test
    public void testGetUserIdThrowsException() throws Exception {
        ForgotUserIdDto fud = new ForgotUserIdDto();
        fud.setAccountnumber(1234567890L);

        when(netBankingUserRepository.findByAccountNumber(fud.getAccountnumber())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            netBankingService.getUserId(fud);
        });
    }

    @Test
    public void testResetPassword() throws Exception {
        ForgotPasswordDto fpd = new ForgotPasswordDto();
        fpd.setUserid("testuser");
        fpd.setPassword("newpassword");

        NetBankingUser user = new NetBankingUser();
        user.setUserId("testuser");
        user.setLoginPassword("oldpassword");

        when(netBankingUserRepository.findByUserId(fpd.getUserid())).thenReturn(Optional.of(user));
        when(netBankingUserRepository.save(user)).thenReturn(user);

        boolean result = netBankingService.resetPassword(fpd);

        assertTrue(result);
        assertEquals(fpd.getPassword(), user.getLoginPassword());
    }

    @Test
    public void testLoginGetUser() throws Exception {
        String userId = "testuser";
        NetBankingUser user = new NetBankingUser();
        user.setUserId(userId);

        when(netBankingUserRepository.findByUserId(userId)).thenReturn(Optional.of(user));

        NetBankingUser result = netBankingService.loginGetUser(userId);

        assertEquals(user, result);
    }

    @Test
    public void testLoginGetUserThrowsException() throws Exception {
        String userId = "testuser";

        when(netBankingUserRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(InvalidLoginException.class, () -> {
            netBankingService.loginGetUser(userId);
        });
    }

    @Test
    public void testRegisterNetBankingUser() throws Exception {
        NetBankingUser user = new NetBankingUser();
        user.setUserId("testuser");
        user.setAccountNumber(1234567890L);
        user.setLoginPassword("password");

        when(netBankingUserRepository.save(user)).thenReturn(user);

        NetBankingUser result = netBankingService.registerNetBankingUser(user);

        assertEquals(user, result);
    }
}