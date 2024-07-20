package com.fortunebank.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.fortunebank.user.dto.CustomerSearchDto;
import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.dto.UserLoginDto;
import com.fortunebank.user.enumtype.TransactionType;
import com.fortunebank.user.service.AdminService;

@SpringBootTest
@ActiveProfiles("test")
public class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGetAllUsers() {
        List<ResponseUserProfile> users = new ArrayList<>();
        ResponseUserProfile user = new ResponseUserProfile();
        user.setFirstName("John");
        user.setLastName("Doe");
        users.add(user);
        when(adminService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<ResponseUserProfile>> response = adminController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        if (response.getBody() != null) {
            assertEquals(users.size(), response.getBody().size());
            assertEquals(users.get(0).getFirstName(), response.getBody().get(0).getFirstName());
            assertEquals(users.get(0).getLastName(), response.getBody().get(0).getLastName());
        }

        verify(adminService, times(1)).getAllUsers();
    }

    @Test
    public void testLoginAdmin() throws Exception {
        UserLoginDto loginDto = new UserLoginDto(); // Populate with mock data
        loginDto.setUserid("admin");
        loginDto.setPassword("admin");
        when(adminService.loginAdmin(loginDto)).thenReturn(true);
        ResponseEntity<String> result = adminController.loginAdmin(loginDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Login Successful", result.getBody());
    }

    @Test
    public void testUpdateAccountStatus() {
        Long accountNumber = 12345L;
        String status = "ENABLED";
        when(adminService.updateAccountStatus(accountNumber, status)).thenReturn(true);
        boolean result = adminController.updateAccountStatus(accountNumber, status);
        assertTrue(result);
    }

    @Test
    public void testGetTransactionsBetweenDates() {
        Date startDate = new Date();
        Date endDate = new Date();
        List<ResponseTransaction> transactions = new ArrayList<>();
        ResponseTransaction transaction = new ResponseTransaction();
        transaction.setAmount(100.0);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transactions.add(transaction);

        when(adminService.getTransactionsBetweenDates(startDate, endDate)).thenReturn(transactions);

        ResponseEntity<List<ResponseTransaction>> response = adminController.getTransactionsBetweenDates(startDate,
                endDate);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        if (response.getBody() != null) {
            assertEquals(transactions.size(), response.getBody().size());
            assertEquals(transactions.get(0).getAmount(), response.getBody().get(0).getAmount());
            assertEquals(transactions.get(0).getTransactionType(), response.getBody().get(0).getTransactionType());
        }
    }

    @Test
    public void testCustomerFirstNameSearch() {
        CustomerSearchDto searchDto = new CustomerSearchDto();
        searchDto.setInput("John");
        List<ResponseUserProfile> users = new ArrayList<>();
        ResponseUserProfile user = new ResponseUserProfile();
        user.setFirstName("John");
        user.setLastName("Doe");
        users.add(user);

        when(adminService.customerFirstNameSearch(searchDto)).thenReturn(users);

        ResponseEntity<List<ResponseUserProfile>> response = adminController.customerFirstNameSearch(searchDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        if (response.getBody() != null) {
            assertEquals(users.size(), response.getBody().size());
            assertEquals(users.get(0).getFirstName(), response.getBody().get(0).getFirstName());
            assertEquals(users.get(0).getLastName(), response.getBody().get(0).getLastName());
        }
    }

    @Test
    public void testCustomerSearchByAccountStatus() {
        CustomerSearchDto searchDto = new CustomerSearchDto();
        List<ResponseUserProfile> users = new ArrayList<>();
        ResponseUserProfile user = new ResponseUserProfile();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAccountStatus("ENABLED");
        users.add(user);

        when(adminService.customerSearchByAccountStatus(searchDto)).thenReturn(users);

        ResponseEntity<List<ResponseUserProfile>> response = adminController.customerSearchByAccountStatus(searchDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        if (response.getBody() != null) {
            assertEquals(users.size(), response.getBody().size());
            assertEquals(users.get(0).getFirstName(), response.getBody().get(0).getFirstName());
            assertEquals(users.get(0).getLastName(), response.getBody().get(0).getLastName());
            assertEquals(users.get(0).getAccountStatus(), response.getBody().get(0).getAccountStatus());
        }
    }

    @Test
    public void testGetAllTransactions() {
        List<ResponseTransaction> transactions = new ArrayList<>();
        transactions.add(new ResponseTransaction());

        when(adminService.getAllTransactions()).thenReturn(transactions);

        ResponseEntity<List<ResponseTransaction>> response = adminController.getAllTransactions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }

    @Test
    public void testGetAllBeneficiaries() {
        List<ResponseBeneficiary> beneficiaries = new ArrayList<>();
        ResponseBeneficiary beneficiary = new ResponseBeneficiary();
        beneficiary.setAccountNumber(12345L);
        beneficiary.setName("John Doe");
        beneficiary.setNickName("John");
        beneficiaries.add(beneficiary);

        when(adminService.getAllBeneficiaries()).thenReturn(beneficiaries);

        ResponseEntity<List<ResponseBeneficiary>> response = adminController.getAllBeneficiaries();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        if (response.getBody() != null) {
            assertEquals(beneficiaries.size(), response.getBody().size());
            assertEquals(beneficiaries.get(0).getAccountNumber(), response.getBody().get(0).getAccountNumber());
            assertEquals(beneficiaries.get(0).getName(), response.getBody().get(0).getName());
            assertEquals(beneficiaries.get(0).getNickName(), response.getBody().get(0).getNickName());
        }
    }
}
