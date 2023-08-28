package com.fortunebank.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.exception.ResourceNotFoundException;
import com.fortunebank.user.dto.PayeeDto;
import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.model.Beneficiary;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.PayeeRepository;
import com.fortunebank.user.utils.HelperFunctions;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PayeeService {

	@Autowired
	private PayeeRepository payeeRepo;

	public Beneficiary addPayee(PayeeDto beneficiary) {
		Beneficiary savedBeneficiary = new Beneficiary();
		UserDetails userDetails = new UserDetails();
		userDetails.setAccountNumber(beneficiary.getAccountnumber());
		UserDetails payeeDetails = new UserDetails();
		payeeDetails.setAccountNumber(beneficiary.getPayeeaccountnumber());

		savedBeneficiary.setUd(userDetails);
		savedBeneficiary.setPayeeDetails(payeeDetails);
		savedBeneficiary.setName(beneficiary.getBeneficiaryname());
		savedBeneficiary.setNickName(beneficiary.getNickname());
		return payeeRepo.save(savedBeneficiary);
	}

	/**
	 * This method is used to get all the beneficiaries of a user.
	 * 
	 * @param Long accountNumber
	 * @return List<ResponseBeneficiary>
	 * @throws ResourceNotFoundException
	 */
	public List<ResponseBeneficiary> findByUdAccountNumber(Long accountNumber) throws ResourceNotFoundException {
		List<ResponseBeneficiary> beneficiaryList = new ArrayList<ResponseBeneficiary>();

		Optional<List<Beneficiary>> bl = payeeRepo.findByUdAccountNumber(accountNumber);
		bl.orElseThrow(() -> new ResourceNotFoundException("Account number not found!"));

		bl.get().stream().forEach(beneficiary -> {
			beneficiaryList.add(HelperFunctions.getResponseBeneficiaryfromPayee(beneficiary));
		});
		return beneficiaryList;
	}

	/**
	 * This method is used to delete a beneficiary.
	 * 
	 * @param accountNumber
	 * @param beneficiaryId
	 * @return Boolean
	 */
	public Boolean deleteBeneficiary(Long accountNumber, Long beneficiaryId) {
		Optional<Beneficiary> beneficiary = payeeRepo.findByUdAccountNumberAndBid(accountNumber, beneficiaryId);
		beneficiary.orElseThrow(() -> new RuntimeException("No Beneficiaries found for the user"));
		payeeRepo.delete(beneficiary.get());
		return true;
	}
}
