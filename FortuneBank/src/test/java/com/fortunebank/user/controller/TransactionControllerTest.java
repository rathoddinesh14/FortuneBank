package com.fortunebank.user.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fortunebank.user.dto.AmountDto;
import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.TransactionDto;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.TransactionService;
import com.fortunebank.user.utils.HelperFunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private TransactionController transactionController;

    @BeforeEach
    public void setup() {
    }

    @AfterEach
    public void teardown() {
    }

    @Test
    public void testTransfer() {
        TransactionDto transactionDto = new TransactionDto();
        UserDetails fromUser = new UserDetails();
        UserDetails toUser = new UserDetails();
        fromUser.setAccountNumber(123456789L);
        toUser.setAccountNumber(987654321L);
        transactionDto.setFromaccount(123456789L);
        transactionDto.setToaccount(987654321L);
        transactionDto.setAmount(100.0);

        Transaction savedTransaction = new Transaction();
        savedTransaction.setFud(fromUser);
        savedTransaction.setTud(toUser);
        savedTransaction.setAmount(100.0);

        when(transactionService.saveTransaction(any(TransactionDto.class)))
                .thenReturn(savedTransaction);

        ResponseEntity<Transaction> responseEntity = transactionController.transfer(transactionDto);
        Transaction result = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        if (result != null) {
            assertEquals(savedTransaction.getFud().getAccountNumber(), result.getFud().getAccountNumber());
            assertEquals(savedTransaction.getTud().getAccountNumber(), result.getTud().getAccountNumber());
            assertEquals(savedTransaction.getAmount(), result.getAmount());
        }
    }

    @Test
    public void testGetTransactions() {
        Long accountNumber = 123456789L;

        List<Transaction> transactions = new ArrayList<>();
        UserDetails fromUser = new UserDetails();
        UserDetails toUser = new UserDetails();
        fromUser.setAccountNumber(123456789L);
        toUser.setAccountNumber(987654321L);

        Transaction savedTransaction = new Transaction();
        savedTransaction.setFud(fromUser);
        savedTransaction.setTud(toUser);
        savedTransaction.setAmount(100.0);
        savedTransaction.setDate(new Date());
        transactions.add(savedTransaction);

        when(transactionService.getTransactions(accountNumber))
                .thenReturn(transactions);

        ResponseEntity<List<ResponseTransaction>> responseEntity = transactionController.getTransactions(accountNumber);
        List<ResponseTransaction> result = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        if (result != null) {
            assertEquals(transactions.size(), result.size());
            assertEquals(transactions.get(0).getFud().getAccountNumber(), result.get(0).getFromAccountNumber());
            assertEquals(transactions.get(0).getTud().getAccountNumber(), result.get(0).getToAccountNumber());
            assertEquals(transactions.get(0).getAmount(), result.get(0).getAmount());
        }
    }

    @Test
    public void testDepositAmountSuccess() {
        AmountDto request = new AmountDto();
        request.setAccountNumber(12345L);
        request.setAmount(100.0);

        when(transactionService.depositAmount(request.getAccountNumber(), request.getAmount()))
                .thenReturn(true);

        ResponseEntity<String> response = transactionController.depositAmount(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deposit successful", response.getBody());

        verify(transactionService).depositAmount(request.getAccountNumber(), request.getAmount());
        verifyNoMoreInteractions(transactionService);
    }

    @Test
    public void testDepositAmountFailure() {
        AmountDto request = new AmountDto();
        request.setAccountNumber(12345L);
        request.setAmount(100.0);

        when(transactionService.depositAmount(request.getAccountNumber(), request.getAmount()))
                .thenReturn(false);

        ResponseEntity<String> response = transactionController.depositAmount(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deposit failed", response.getBody());

        verify(transactionService).depositAmount(request.getAccountNumber(), request.getAmount());
        verifyNoMoreInteractions(transactionService);
    }

    @Test
    public void testWithdrawAmountSuccess() {
        AmountDto request = new AmountDto();
        request.setAccountNumber(12345L);
        request.setAmount(50.0);

        when(transactionService.withdrawAmount(request.getAccountNumber(), request.getAmount()))
                .thenReturn(true);

        ResponseEntity<String> response = transactionController.withdrawAmount(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Withdrawal successful", response.getBody());

        verify(transactionService).withdrawAmount(request.getAccountNumber(), request.getAmount());
        verifyNoMoreInteractions(transactionService);
    }

    @Test
    public void testWithdrawAmountFailure() {
        AmountDto request = new AmountDto();
        request.setAccountNumber(12345L);
        request.setAmount(50.0);

        when(transactionService.withdrawAmount(request.getAccountNumber(), request.getAmount()))
                .thenReturn(false);

        ResponseEntity<String> response = transactionController.withdrawAmount(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Withdrawal failed", response.getBody());

        verify(transactionService).withdrawAmount(request.getAccountNumber(), request.getAmount());
        verifyNoMoreInteractions(transactionService);
    }

    @Test
    public void testWithdrawAmountInvalidAccount() {
        AmountDto request = new AmountDto();
        request.setAccountNumber(12345L);
        request.setAmount(50.0);

        String errorMessage = "Invalid user account";
        when(transactionService.withdrawAmount(request.getAccountNumber(), request.getAmount()))
                .thenThrow(new IllegalArgumentException(errorMessage));

        ResponseEntity<String> response = transactionController.withdrawAmount(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());

        verify(transactionService).withdrawAmount(request.getAccountNumber(), request.getAmount());
        verifyNoMoreInteractions(transactionService);
    }

    @Test
    public void testGetTransactionsBetweenDates() {
        Long accountNumber = 12345L;
        Date startDate = new Date();
        Date endDate = new Date();

        Transaction transaction1 = new Transaction();
        transaction1.setTid(1L);
        transaction1.setAmount(50.0);
        transaction1.setDate(new Date());
        transaction1.setFud(new UserDetails());
        transaction1.setTud(new UserDetails());

        Transaction transaction2 = new Transaction();
        transaction2.setTid(2L);
        transaction2.setAmount(100.0);
        transaction2.setDate(new Date());
        transaction2.setFud(new UserDetails());
        transaction2.setTud(new UserDetails());

        List<Transaction> transactionList = Arrays.asList(transaction1, transaction2);
        when(transactionService.getTransactionsBetweenDates(accountNumber, startDate, endDate))
                .thenReturn(transactionList);

        List<ResponseTransaction> expectedResponseList = new ArrayList<>();
        expectedResponseList.add(HelperFunctions.getResponseTransactionfromTransaction(transaction1));
        expectedResponseList.add(HelperFunctions.getResponseTransactionfromTransaction(transaction2));

        List<ResponseTransaction> response = transactionController.getTransactionsBetweenDates(accountNumber, startDate,
                endDate);

        assertEquals(expectedResponseList.size(), response.size());
        assertEquals(expectedResponseList.get(0).getAmount(), response.get(0).getAmount());
        assertEquals(expectedResponseList.get(1).getAmount(), response.get(1).getAmount());

        verify(transactionService).getTransactionsBetweenDates(accountNumber, startDate, endDate);
        verifyNoMoreInteractions(transactionService);
    }
}
