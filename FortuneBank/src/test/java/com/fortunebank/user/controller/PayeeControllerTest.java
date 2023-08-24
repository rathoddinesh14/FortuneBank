package com.fortunebank.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.fortunebank.user.dto.PayeeDto;
import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.model.Beneficiary;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.PayeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PayeeControllerTest {

    @Mock
    private PayeeService beneficiaryService;

    @InjectMocks
    private PayeeController payeeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBeneficiary() {
        PayeeDto payeeDto = new PayeeDto();
        // Set properties for payeeDto

        Beneficiary savedBeneficiary = new Beneficiary();
        // Set properties for savedBeneficiary
        
        when(beneficiaryService.addPayee(any(Beneficiary.class)))
            .thenReturn(savedBeneficiary);

        ResponseEntity<Beneficiary> responseEntity = payeeController.addBeneficiary(payeeDto);
        Beneficiary result = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedBeneficiary, result);
    }

    @Test
    public void testGetBeneficiary() {
        Long accountNumber = 123456789L;

        List<Beneficiary> beneficiaries = new ArrayList<>();
        // Add Beneficiary instances to the list
        
        when(beneficiaryService.findByUdAccountNumber(accountNumber))
            .thenReturn(beneficiaries);

        ResponseEntity<List<ResponseBeneficiary>> responseEntity = payeeController.getBeneficiary(accountNumber);
        List<ResponseBeneficiary> result = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(beneficiaries.size(), result.size()); // Adjust the assertion based on your data
    }
}

