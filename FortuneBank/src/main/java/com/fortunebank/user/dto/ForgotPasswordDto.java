package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ForgotPasswordDto {
    private String userid;
    private String password;
    private String otp;
}
