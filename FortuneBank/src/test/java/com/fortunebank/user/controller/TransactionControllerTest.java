package com.fortunebank.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.TransactionDto;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.TransactionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTransfer() {
        TransactionDto transactionDto = new TransactionDto();
        // Set properties for transactionDto
        
        Transaction savedTransaction = new Transaction();
        // Set properties for savedTransaction
        
        when(transactionService.saveTransaction(any(Transaction.class)))
            .thenReturn(savedTransaction);

        ResponseEntity<Transaction> responseEntity = transactionController.transfer(transactionDto);
        Transaction result = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(savedTransaction, result);
    }


    @Test
    public void testGetTransactions() {
        Long accountNumber = 123456789L;

        List<Transaction> transactions = new ArrayList<>();
        // Add Transaction instances to the list
        
        when(transactionService.getTransactions(accountNumber))
            .thenReturn(transactions);

        ResponseEntity<List<ResponseTransaction>> responseEntity = transactionController.getTransactions(accountNumber);
        List<ResponseTransaction> result = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactions.size(), result.size()); // Adjust the assertion based on your data
    }
}

