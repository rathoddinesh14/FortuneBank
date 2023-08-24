package com.fortunebank.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.model.Beneficiary;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.repository.PayeeRepository;
import com.fortunebank.user.utils.HelpferFunctions;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PayeeService {

	@Autowired
	private PayeeRepository payeeRepo;

	public Beneficiary addPayee(Beneficiary ben) {
		return payeeRepo.save(ben);
	}

	public List<ResponseBeneficiary> findByUdAccountNumber(Long accountNumber) {
		List<ResponseBeneficiary> beneficiaryList = new ArrayList<ResponseBeneficiary>();

		Optional<List<Beneficiary>> bl = payeeRepo.findByUdAccountNumber(accountNumber);
		bl.orElseThrow(() -> new RuntimeException("No Beneficiaries found for the user"));

		bl.get().stream().forEach(beneficiary -> {
			beneficiaryList.add(HelpferFunctions.getResponseBeneficiaryfromPayee(beneficiary));
		});
		return beneficiaryList;
	}

	public Boolean deleteBeneficiary(Long accountNumber, Long beneficiaryId) {
		Optional<Beneficiary> beneficiary = payeeRepo.findByUdAccountNumberAndBid(accountNumber, beneficiaryId);
		beneficiary.orElseThrow(() -> new RuntimeException("No Beneficiaries found for the user"));
		payeeRepo.delete(beneficiary.get());
		return true;
	}
}
