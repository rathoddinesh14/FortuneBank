package com.fortunebank.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddPayeeDto {
	private String beneficiaryname;
	private Long accountnumber;
	private boolean savebeneficiary;
	private String nickname;
	
	
}
