package com.fortunebank.user.dto;

import com.fortunebank.user.enumtype.TransactionType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ResponseTransaction {
    private Long tid;
    private Long fromAccountNumber;
    private Long toAccountNumber;
    private double amount;
    private String date;
    private String remark;
    private String maturityInstructions;
    private TransactionType transactionType;
}
