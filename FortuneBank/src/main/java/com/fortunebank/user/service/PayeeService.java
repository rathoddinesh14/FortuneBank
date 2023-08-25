package com.fortunebank.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.exception.ResourceNotFoundException;
import com.fortunebank.user.model.Beneficiary;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.repository.PayeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PayeeService {
	
	@Autowired
	private PayeeRepository payeeRepo;
	
	 public Beneficiary addPayee(Beneficiary ben) {
	        return payeeRepo.save(ben);
	    }
	 
	 public List<Beneficiary> findByUdAccountNumber(Long accountNumber) throws ResourceNotFoundException {
	    List<Beneficiary> payees =  payeeRepo.findByUdAccountNumber(accountNumber)
	    		.orElseThrow(() -> new ResourceNotFoundException("Account number not found!"));  
		 return payees;
	    }
}
