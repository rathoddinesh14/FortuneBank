package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class TransactionDto {
    private Long fromAccount;
    private Long toAccount;
    private Double amount;
    private String remarks;
}