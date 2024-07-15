package com.fortunebank.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.PayeeDto;
import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.exception.ResourceNotFoundException;
import com.fortunebank.user.model.Beneficiary;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.PayeeService;

@RestController
@RequestMapping("/api/beneficiary")
public class PayeeController {
    @Autowired
    private PayeeService beneficiaryService;

    /*
     * This method is used to add a beneficiary
     * 
     * @param PayeeDto beneficiary
     * 
     * @return ResponseEntity<Beneficiary>
     */
    @PostMapping("/add")
    public ResponseEntity<String> addBeneficiary(@RequestBody PayeeDto beneficiary) {
        try {
            beneficiaryService.addPayee(beneficiary);
            return new ResponseEntity<String>("Beneficiary added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Beneficiary not added", HttpStatus.OK);
        }
    }

    /*
     * This method is used to get the beneficiaries of a particular account
     * 
     * @param Long accountNumber
     * 
     * @return ResponseEntity<List<ResponseBeneficiary>>
     */
    @GetMapping("/get/{accountNumber}")
    public ResponseEntity<List<ResponseBeneficiary>> getBeneficiary(@PathVariable Long accountNumber)
            throws ResourceNotFoundException {
        return new ResponseEntity<List<ResponseBeneficiary>>(beneficiaryService.findByUdAccountNumber(accountNumber),
                HttpStatus.OK);
    }

    /*
     * This method is used to delete a beneficiary
     * 
     * @param Long accountNumber
     * 
     * @param Long beneficiaryId
     * 
     * @return ResponseEntity<Boolean>
     */
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