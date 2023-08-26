package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResponseBeneficiary {
    private Long bid;
    private Long payeeAccountNumber;
    private String name;
    private String nickName;
    private Long accountNumber;
}
