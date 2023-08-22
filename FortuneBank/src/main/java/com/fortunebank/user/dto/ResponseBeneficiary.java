package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResponseBeneficiary {
    private Long accountnumber;
    private String name;
    private String nickname;
}
