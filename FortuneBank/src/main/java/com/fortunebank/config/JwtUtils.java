package com.fortunebank.config;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtUtils {

    private static final String SECRET_KEY = "mySecretKey";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    private static int expirationTime = 600000;

    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime)) // 10 minutes
                .sign(ALGORITHM);
    }

    public static String verifyToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public static int getExpirationTime() {
        return expirationTime;
    }

    public static void setExpirationTime(int expirationTime) {
        JwtUtils.expirationTime = expirationTime;
    }

}
