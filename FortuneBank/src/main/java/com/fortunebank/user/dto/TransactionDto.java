package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing transaction information.
 */
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto {
    /**
     * The account number from which the transaction is made.
     */
    private Long fromaccount;

    /**
     * The account number to which the transaction is made.
     */
    private Long toaccount;

    /**
     * The amount of the transaction.
     */
    private Double amount;

    /**
     * Remarks or description for the transaction.
     */
    private String remarks;

    /**
     * Instructions for the maturity of the transaction.
     */
    private String maturityInstructions;

    /**
     * The mode of the transaction.
     */
    private String mode;
}
