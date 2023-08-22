package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private String line1;
    private String line2;
    private String landmark;
    private String state;
    private String city;
    private int pincode;
    private String type;
}
