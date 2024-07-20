package com.fortunebank.user.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.test.context.ActiveProfiles;

import com.fortunebank.user.dto.PayeeDto;
import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.exception.ResourceNotFoundException;
import com.fortunebank.user.model.Beneficiary;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.PayeeRepository;

@SpringBootTest
@ActiveProfiles("test")
public class PayeeServiceTest {

    @MockBean
    private PayeeRepository payeeRepository;

    @Autowired
    private PayeeService payeeService;

    UserDetails refUserDetails = null;

    @BeforeEach
    public void setUp() {
        // Initialize the user details
        refUserDetails = new UserDetails();
        refUserDetails.setAccountNumber(12345L);
    }

    @AfterEach
    public void tearDown() {
        // Reset the mock repository
        refUserDetails = null;
    }

    @Test
    public void testAddPayee() {
        PayeeDto payeeDto = new PayeeDto();
        payeeDto.setAccountnumber(12345L);
        payeeDto.setPayeeaccountnumber(54321L);
        payeeDto.setBeneficiaryname("John Doe");
        payeeDto.setNickname("John's Account");

        Beneficiary expectedBeneficiary = new Beneficiary();
        UserDetails userDetails = new UserDetails();
        userDetails.setAccountNumber(12345L);
        UserDetails payeeDetails = new UserDetails();
        payeeDetails.setAccountNumber(54321L);

        expectedBeneficiary.setUd(userDetails);
        expectedBeneficiary.setPayeeDetails(payeeDetails);
        expectedBeneficiary.setName("John Doe");
        expectedBeneficiary.setNickName("John's Account");

        when(payeeRepository.save(any(Beneficiary.class))).thenReturn(expectedBeneficiary);

        Beneficiary addedBeneficiary = payeeService.addPayee(payeeDto);

        // assert the account number, beneficiary name, nickname, etc.
        assertEquals(expectedBeneficiary.getUd().getAccountNumber(), addedBeneficiary.getUd().getAccountNumber());
        assertEquals(expectedBeneficiary.getPayeeDetails().getAccountNumber(), addedBeneficiary.getPayeeDetails().getAccountNumber());
        assertEquals(expectedBeneficiary.getName(), addedBeneficiary.getName());
        assertEquals(expectedBeneficiary.getNickName(), addedBeneficiary.getNickName());
    }

    @Test
    public void testFindByUdAccountNumber() {
        UserDetails refUserDetails = new UserDetails();
        refUserDetails.setAccountNumber(12345L);

        List<Beneficiary> beneficiaries = new ArrayList<>();
        
        // Initialize the beneficiary
        Beneficiary beneficiary = new Beneficiary();
        UserDetails userDetails = new UserDetails();
        userDetails.setAccountNumber(123L);
        beneficiary.setBid(1L);
        beneficiary.setPayeeDetails(userDetails);
        beneficiary.setUd(refUserDetails);
        beneficiary.setName("John Doe");
        beneficiary.setNickName("John");

        Beneficiary beneficiary2 = new Beneficiary();
        UserDetails userDetails2 = new UserDetails();
        userDetails2.setAccountNumber(124L);
        beneficiary2.setBid(2L);
        beneficiary2.setUd(refUserDetails);
        beneficiary2.setPayeeDetails(userDetails2);
        beneficiary2.setName("Jane Doe");
        beneficiary2.setNickName("Jane");
        
        beneficiaries.add(beneficiary);
        beneficiaries.add(beneficiary2);

        when(payeeRepository.findByUdAccountNumber(refUserDetails.getAccountNumber())).thenReturn(Optional.of(beneficiaries));

        try {
            List<ResponseBeneficiary> responseBeneficiaries = payeeService.findByUdAccountNumber(refUserDetails.getAccountNumber());
            
            // Assert the size of the list
            assertEquals(beneficiaries.size(), responseBeneficiaries.size());

            // Assert the first beneficiary
            assertEquals(beneficiaries.get(0).getName(), responseBeneficiaries.get(0).getName());
            assertEquals(beneficiaries.get(0).getNickName(), responseBeneficiaries.get(0).getNickName());
            assertEquals(beneficiaries.get(0).getBid(), responseBeneficiaries.get(0).getBid());
            assertEquals(beneficiaries.get(0).getPayeeDetails().getAccountNumber(), responseBeneficiaries.get(0).getPayeeAccountNumber());

            // Assert the second beneficiary
            assertEquals(beneficiaries.get(1).getName(), responseBeneficiaries.get(1).getName());
            assertEquals(beneficiaries.get(1).getNickName(), responseBeneficiaries.get(1).getNickName());
            assertEquals(beneficiaries.get(1).getBid(), responseBeneficiaries.get(1).getBid());
            assertEquals(beneficiaries.get(1).getPayeeDetails().getAccountNumber(), responseBeneficiaries.get(1).getPayeeAccountNumber());

        } catch (ResourceNotFoundException e) {
            // Handle the exception if needed
        }
    }

    @Test
    public void testDeleteBeneficiary() {

        Beneficiary beneficiary = new Beneficiary();
        // Initialize the beneficiary
        beneficiary.setBid(1L);
        beneficiary.setName("John Doe");
        beneficiary.setNickName("John");
        beneficiary.setUd(refUserDetails);

        when(payeeRepository.findByUdAccountNumberAndBid(refUserDetails.getAccountNumber(), beneficiary.getBid())).thenReturn(Optional.of(beneficiary));

        Boolean result = payeeService.deleteBeneficiary(refUserDetails.getAccountNumber(), beneficiary.getBid());

        // Assert the result
        assertEquals(true, result);
    }
}
