package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing the amount to be used in
 * transactions.
 */
@Getter
@Setter
public class AmountDto {
    /**
     * The account number associated with the transaction.
     */
    private Long accountNumber;

    /**
     * The amount to be used in the transaction.
     */
    private double amount;
}
