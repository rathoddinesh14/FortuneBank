package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUserProfile {
    private Long accountNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fatherName;
    private String phone;
    private String email;
    private String aadharNumber;
    private String dob;
    private int accountType;
    private double balance;
    private String accountStatus;
}
