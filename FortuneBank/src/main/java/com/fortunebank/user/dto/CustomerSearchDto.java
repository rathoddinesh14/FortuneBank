package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a search input for customer
 * search operations.
 */
@Getter
@Setter
public class CustomerSearchDto {
    /**
     * The input value used for customer search.
     */
    private String input;
}
