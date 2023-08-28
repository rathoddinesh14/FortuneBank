package com.fortunebank.user.dto;

import com.fortunebank.user.enumtype.TransactionMode;
import com.fortunebank.user.enumtype.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing transaction information in the
 * response.
 */
@NoArgsConstructor
@Getter
@Setter
public class ResponseTransaction {
    /**
     * The transaction ID.
     */
    private Long tid;

    /**
     * The account number from which the transaction occurred.
     */
    private Long fromAccountNumber;

    /**
     * The account number to which the transaction occurred.
     */
    private Long toAccountNumber;

    /**
     * The amount of the transaction.
     */
    private double amount;

    /**
     * The date of the transaction.
     */
    private String date;

    /**
     * A remark or description of the transaction.
     */
    private String remark;

    /**
     * Instructions related to the maturity of the transaction.
     */
    private String maturityInstructions;

    /**
     * The type of transaction (e.g., Deposit, Withdrawal, Transfer).
     */
    private TransactionType transactionType;

    /**
     * The mode of the transaction (e.g., NEFT, IMPS, RTGS, SELF).
     */
    private TransactionMode transactionMode;
}
