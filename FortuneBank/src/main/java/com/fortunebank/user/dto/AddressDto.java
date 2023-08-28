package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing address information.
 */
@Getter
@Setter
public class AddressDto {
    /**
     * The first line of the address.
     */
    private String line1;

    /**
     * The second line of the address.
     */
    private String line2;

    /**
     * A landmark or reference point near the address.
     */
    private String landmark;

    /**
     * The state or province of the address.
     */
    private String state;

    /**
     * The city or locality of the address.
     */
    private String city;

    /**
     * The postal code or PIN code of the address.
     */
    private int pincode;

    /**
     * The type of address (e.g., Temporary, Permanent).
     */
    private String type;
}
