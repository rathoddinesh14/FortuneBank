package com.fortunebank.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fortunebank.user.model.Beneficiary;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.repository.PayeeRepository;

public class PayeeService {
	
	@Autowired
	private PayeeRepository payeeRepo;
	
	 public Beneficiary addPayee(Beneficiary ben) {
	        return payeeRepo.save(ben);
	    }
	 
	 public List<Beneficiary> findByUdAccountNumber(Long accountNumber) {
	        return payeeRepo.findByUdAccountNumber(accountNumber);
	    }
}
