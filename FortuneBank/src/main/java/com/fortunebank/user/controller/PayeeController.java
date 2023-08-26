package com.fortunebank.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.PayeeDto;
import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.model.Beneficiary;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.PayeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/beneficiary")
public class PayeeController {
    @Autowired
    private PayeeService beneficiaryService;

    @PostMapping("/add")
    public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody PayeeDto beneficiary) {
        Beneficiary savedBeneficiary = new Beneficiary();
        UserDetails userDetails = new UserDetails();
        userDetails.setAccountNumber(beneficiary.getAccountnumber());
        UserDetails payeeDetails = new UserDetails();
        payeeDetails.setAccountNumber(beneficiary.getPayeeaccountnumber());
        savedBeneficiary.setUd(userDetails);
        savedBeneficiary.setPayeeDetails(payeeDetails);
        savedBeneficiary.setName(beneficiary.getBeneficiaryname());
        savedBeneficiary.setNickName(beneficiary.getNickname());
        savedBeneficiary = beneficiaryService.addPayee(savedBeneficiary);
        return new ResponseEntity<Beneficiary>(savedBeneficiary, HttpStatus.CREATED);
    }

    @GetMapping("/get/{accountNumber}")
    public ResponseEntity<List<ResponseBeneficiary>> getBeneficiary(@PathVariable Long accountNumber) {
        return new ResponseEntity<List<ResponseBeneficiary>>(beneficiaryService.findByUdAccountNumber(accountNumber),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{accountNumber}/{beneficiaryId}")
    public ResponseEntity<Boolean> deleteBeneficiary(@PathVariable Long accountNumber,
            @PathVariable Long beneficiaryId) {
        try {
            return new ResponseEntity<Boolean>(beneficiaryService.deleteBeneficiary(accountNumber, beneficiaryId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
    }
}