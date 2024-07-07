package com.fortunebank.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.auth0.jwt.exceptions.TokenExpiredException;

public class JwtUtilsTest {

    @BeforeEach
    public void setUp(TestInfo info) {
        System.out.println("setup " + info.getDisplayName());
    }

    @AfterEach
    public void tearDown() {
        System.out.println("teardown");
    }

    @Test
    public void testGenerateToken() {
        String username = "testUser";
        String token = JwtUtils.generateToken(username);

        Assertions.assertNotNull(token);
        Assertions.assertFalse(token.isEmpty());
    }

    @Test
    public void testVerifyToken() {
        String username = "testUser";
        String token = JwtUtils.generateToken(username);

        String verifiedUsername = JwtUtils.verifyToken(token);

        Assertions.assertEquals(username, verifiedUsername);
    }

    @Test
    public void testTokenExpiration() {
        String username = "testUser";
        JwtUtils.setExpirationTime(5000);
        String token = JwtUtils.generateToken(username);

        try {
            // Simulate waiting for token to expire
            Thread.sleep(10000); // 10 secs
        } catch (InterruptedException ex) {
        }

        Assertions.assertThrows(TokenExpiredException.class, () -> {
            JwtUtils.verifyToken(token);
        });
    }
}
