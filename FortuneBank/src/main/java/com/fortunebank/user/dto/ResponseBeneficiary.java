package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing the response for a beneficiary.
 */
@NoArgsConstructor
@Getter
@Setter
public class ResponseBeneficiary {
    /**
     * Beneficiary ID.
     */
    private Long bid;

    /**
     * Payee's account number.
     */
    private Long payeeAccountNumber;

    /**
     * Beneficiary's name.
     */
    private String name;

    /**
     * Nickname associated with the beneficiary.
     */
    private String nickName;

    /**
     * Account number associated with the beneficiary.
     */
    private Long accountNumber;
}
