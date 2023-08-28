package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing the information required for
 * the forgot user ID process.
 */
@NoArgsConstructor
@Getter
@Setter
public class ForgotUserIdDto {
    /**
     * The account number associated with the user.
     */
    private Long accountnumber;

    /**
     * The one-time password (OTP) for verification.
     */
    private String otp;
}
