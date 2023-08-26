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
import com.fortunebank.user.service.PayeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class PayeeControllerTest {

    @MockBean
    private PayeeService beneficiaryService;

    @Autowired
    private PayeeController payeeController;

    @BeforeEach
    public void setup() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddBeneficiary() {
        PayeeDto payeeDto = new PayeeDto();

        Beneficiary savedBeneficiary = new Beneficiary();

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

        List<ResponseBeneficiary> beneficiaries = new ArrayList<>();

        when(beneficiaryService.findByUdAccountNumber(accountNumber))
                .thenReturn(beneficiaries);

        ResponseEntity<List<ResponseBeneficiary>> responseEntity = payeeController.getBeneficiary(accountNumber);
        List<ResponseBeneficiary> result = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        if (result != null)
            assertEquals(beneficiaries.size(), result.size()); // Adjust the assertion based on your data
    }

    @Test
    public void testDeleteBeneficiary() {
        Long accountNumber = 123456789L;
        Long beneficiaryId = 1L;

        when(beneficiaryService.deleteBeneficiary(accountNumber, beneficiaryId))
                .thenReturn(true);

        ResponseEntity<Boolean> responseEntity = payeeController.deleteBeneficiary(accountNumber, beneficiaryId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody());
    }
}
