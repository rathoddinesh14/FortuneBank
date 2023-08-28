package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing the information required for
 * the forgot password process.
 */
@NoArgsConstructor
@Getter
@Setter
public class ForgotPasswordDto {
    /**
     * The user ID associated with the account.
     */
    private String userid;

    /**
     * The new password to be set.
     */
    private String password;

    /**
     * The one-time password (OTP) for verification.
     */
    private String otp;
}
