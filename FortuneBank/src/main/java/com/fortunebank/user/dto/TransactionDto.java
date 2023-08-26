package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class TransactionDto {
    private Long fromaccount;
    private Long toaccount;
    private Double amount;
    private String remarks;
    private String maturityInstructions;
}