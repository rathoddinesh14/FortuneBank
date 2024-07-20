package com.fortunebank.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.fortunebank.user.dto.CustomerSearchDto;
import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.dto.UserLoginDto;
import com.fortunebank.user.enumtype.AccountStatus;
import com.fortunebank.user.exception.InvalidLoginException;
import com.fortunebank.user.model.Beneficiary;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.PayeeRepository;
import com.fortunebank.user.repository.TransactionRepository;
import com.fortunebank.user.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private PayeeRepository payeeRepository;

    @MockBean
    private UserService userService;

    UserDetails user1 = null;
    UserDetails user2 = null;

    @BeforeEach
    public void setUp() {
        user1 = new UserDetails();
        user1.setAccountNumber(1L);
        user1.setFirstName("John");
        user1.setMiddleName("Doe");
        user1.setLastName("Smith");
        user1.setFatherName("John Wick");
        user1.setEmail("johndoe@email.com");
        user1.setDob(Date.valueOf("2000-01-01"));
        user1.setAccountStatus(AccountStatus.ENABLED);
        user1.setAadharNumber("123456789012");
        user1.setPhone("1234567890");

        user2 = new UserDetails();
        user2.setAccountNumber(2L);
        user2.setFirstName("Jane");
        user2.setDob(Date.valueOf("2000-01-01"));
        user2.setAccountStatus(AccountStatus.ENABLED);
    }

    @AfterEach
    public void tearDown() {
        user1 = null;
        user2 = null;
    }


    @Test
    public void testGetAllUsers() {
        List<UserDetails> users = new ArrayList<>();
        
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<ResponseUserProfile> responseUsers = adminService.getAllUsers();

        assertEquals(2, responseUsers.size());
        assertEquals("John", responseUsers.get(0).getFirstName());
        assertEquals(1L, responseUsers.get(0).getAccountNumber());
        assertEquals("Jane", responseUsers.get(1).getFirstName());
        assertEquals(2L, responseUsers.get(1).getAccountNumber());
    }

    @Test
    public void testGetAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction();
        transaction1.setTid(1L);
        transaction1.setAmount(1000.0);
        transaction1.setDate(Date.valueOf("2020-01-01"));
        transaction1.setFud(user1);
        transaction1.setTud(user2);

        Transaction transaction2 = new Transaction();
        transaction2.setTid(2L);
        transaction2.setAmount(2000.0);
        transaction2.setDate(Date.valueOf("2020-01-01"));
        transaction2.setFud(user2);
        transaction2.setTud(user1);

        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<ResponseTransaction> responseTransactions = adminService.getAllTransactions();

        assertEquals(2, responseTransactions.size());
        assertEquals(1L, responseTransactions.get(0).getTid());
        assertEquals(1000.0, responseTransactions.get(0).getAmount());
        assertEquals(2L, responseTransactions.get(1).getTid());
        assertEquals(2000.0, responseTransactions.get(1).getAmount());
    }

    @Test
    public void testGetAllBeneficiaries() {
        // create a list of beneficiaries to be returned by the repository
        List<Beneficiary> beneficiaries = new ArrayList<>();
        Beneficiary beneficiary1 = new Beneficiary();
        beneficiary1.setBid(1L);
        beneficiary1.setName("Jane");
        beneficiary1.setUd(user1);
        beneficiary1.setPayeeDetails(user2);
        beneficiaries.add(beneficiary1);

        Beneficiary beneficiary2 = new Beneficiary();
        beneficiary2.setBid(2L);
        beneficiary2.setName("John");
        beneficiary2.setUd(user2);
        beneficiary2.setPayeeDetails(user1);
        beneficiaries.add(beneficiary2);

        // mock the beneficiary repository to return the list of beneficiaries
        when(payeeRepository.findAll()).thenReturn(beneficiaries);

        // call the method being tested
        List<ResponseBeneficiary> responseBeneficiaries = adminService.getAllBeneficiaries();

        // assert that the response contains the expected number of beneficiaries
        assertEquals(2, responseBeneficiaries.size());

        // assert that the response contains the expected beneficiaries
        assertEquals("Jane", responseBeneficiaries.get(0).getName());
        assertEquals(1L, responseBeneficiaries.get(0).getBid());
        assertEquals("John", responseBeneficiaries.get(1).getName());
        assertEquals(2L, responseBeneficiaries.get(1).getBid());
    }

    @Test
    public void testLoginAdminWithCorrectCredentials() {
        UserLoginDto uld = new UserLoginDto("admin", "admin");
        boolean result = adminService.loginAdmin(uld);
        assertTrue(result);
    }

    @Test
    public void testLoginAdminWithIncorrectCredentials() {
        UserLoginDto uld = new UserLoginDto("admin", "wrongpassword");
        assertThrows(InvalidLoginException.class, () -> adminService.loginAdmin(uld));
    }

    @Test
    public void testLoginAdminWithEmptyCredentials() {
        UserLoginDto uld = new UserLoginDto("", "");
        assertThrows(InvalidLoginException.class, () -> adminService.loginAdmin(uld));
    }

    @Test
    public void testUpdateAccountStatus() {
        Long accountNumber = 1L;
        String status = "DISABLED";
        boolean expectedResult = true;

        when(userService.updateAccountStatus(accountNumber, AccountStatus.DISABLED)).thenReturn(expectedResult);

        boolean actualResult = adminService.updateAccountStatus(accountNumber, status);

        assertEquals(expectedResult, actualResult);
        verify(userService, times(1)).updateAccountStatus(accountNumber, AccountStatus.DISABLED);
    }


    @Test
    public void testCustomerAccountNumberSearch() {
        CustomerSearchDto searchDto = new CustomerSearchDto();
        searchDto.setInput("1");

        UserDetails user = new UserDetails();
        user.setAccountNumber(1L);
        user.setFirstName("John");
        user.setDob(Date.valueOf("2000-01-01"));
        user.setAccountStatus(AccountStatus.ENABLED);

        when(userRepository.findByAccountNumber(1L)).thenReturn(Optional.of(user));

        List<ResponseUserProfile> responseUsers = adminService.customerAccountNumberSearch(searchDto);

        assertEquals(1, responseUsers.size());
        assertEquals("John", responseUsers.get(0).getFirstName());
        assertEquals(1L, responseUsers.get(0).getAccountNumber());
    }

    @Test
    public void testCustomerFirstNameSearch() {
        CustomerSearchDto searchDto = new CustomerSearchDto();
        searchDto.setInput("John");

        List<UserDetails> users = new ArrayList<>();
        users.add(user1);

        when(userRepository.findByFirstName("John")).thenReturn(users);

        List<ResponseUserProfile> responseUsers = adminService.customerFirstNameSearch(searchDto);

        assertEquals(1, responseUsers.size());
        assertEquals("John", responseUsers.get(0).getFirstName());
        assertEquals(1L, responseUsers.get(0).getAccountNumber());
    }

    @Test
    public void testCustomerMiddleNameSearch() {
        CustomerSearchDto searchDto = new CustomerSearchDto();
        searchDto.setInput("Doe");

        List<UserDetails> users = new ArrayList<>();
        users.add(user1);

        when(userRepository.findByMiddleName("Doe")).thenReturn(users);

        List<ResponseUserProfile> responseUsers = adminService.customerMiddleNameSearch(searchDto);

        assertEquals(1, responseUsers.size());
        assertEquals("Doe", responseUsers.get(0).getMiddleName());
        assertEquals(1L, responseUsers.get(0).getAccountNumber());
    }

    @Test
    public void testCustomerLastNameSearch() {
        CustomerSearchDto searchDto = new CustomerSearchDto();
        searchDto.setInput("Smith");

        List<UserDetails> users = new ArrayList<>();
        users.add(user1);

        when(userRepository.findByLastName("Smith")).thenReturn(users);

        List<ResponseUserProfile> responseUsers = adminService.customerLastNameSearch(searchDto);

        assertEquals(1, responseUsers.size());
        assertEquals("Smith", responseUsers.get(0).getLastName());
        assertEquals(1L, responseUsers.get(0).getAccountNumber());
    }

    @Test
    public void testCustomerPhoneSearch() {
        CustomerSearchDto searchDto = new CustomerSearchDto();
        searchDto.setInput("1234567890");

        List<UserDetails> users = new ArrayList<>();
        users.add(user1);

        when(userRepository.findByPhone("1234567890")).thenReturn(users);

        List<ResponseUserProfile> responseUsers = adminService.customerPhoneSearch(searchDto);

        assertEquals(1, responseUsers.size());
        assertEquals("John", responseUsers.get(0).getFirstName());
        assertEquals(1L, responseUsers.get(0).getAccountNumber());
        assertEquals("1234567890", responseUsers.get(0).getPhone());
    }

    @Test
    public void testCustomerFatherNameSearch() {
        CustomerSearchDto searchDto = new CustomerSearchDto();
        searchDto.setInput("John Wick");

        List<UserDetails> users = new ArrayList<>();
        users.add(user1);

        when(userRepository.findByFatherName("John Wick")).thenReturn(users);

        List<ResponseUserProfile> responseUsers = adminService.customerFatherNameSearch(searchDto);

        assertEquals(1, responseUsers.size());
        assertEquals("John", responseUsers.get(0).getFirstName());
        assertEquals(1L, responseUsers.get(0).getAccountNumber());
        assertEquals("John Wick", responseUsers.get(0).getFatherName());
    }

    @Test
    public void testCustomerEmailSearch() {
        CustomerSearchDto searchDto = new CustomerSearchDto();
        searchDto.setInput("johndoe@email.com");

        List<UserDetails> users = new ArrayList<>();
        users.add(user1);

        when(userRepository.findByEmail("johndoe@email.com")).thenReturn(users);

        List<ResponseUserProfile> responseUsers = adminService.customerEmailSearch(searchDto);

        assertEquals(1, responseUsers.size());
        assertEquals("John", responseUsers.get(0).getFirstName());
        assertEquals(1L, responseUsers.get(0).getAccountNumber());
        assertEquals("johndoe@email.com", responseUsers.get(0).getEmail());
    }

    @Test
    public void testCustomerAadharNumberSearch() {
        CustomerSearchDto searchDto = new CustomerSearchDto();
        searchDto.setInput("123456789012");

        List<UserDetails> users = new ArrayList<>();
        users.add(user1);

        when(userRepository.findByAadharNumber("123456789012")).thenReturn(users);

        List<ResponseUserProfile> responseUsers = adminService.customerAadharNumberSearch(searchDto);

        assertEquals(1, responseUsers.size());
        assertEquals("John", responseUsers.get(0).getFirstName());
        assertEquals(1L, responseUsers.get(0).getAccountNumber());
        assertEquals("123456789012", responseUsers.get(0).getAadharNumber());
    }

    @Test
    public void testCustomerSearchByAccountStatus() {
        CustomerSearchDto searchDto = new CustomerSearchDto();
        searchDto.setInput("ENABLED");

        List<UserDetails> users = new ArrayList<>();
        users.add(user1);

        when(userRepository.findByAccountStatus(AccountStatus.ENABLED)).thenReturn(users);

        List<ResponseUserProfile> responseUsers = adminService.customerSearchByAccountStatus(searchDto);

        assertEquals(1, responseUsers.size());
        assertEquals("John", responseUsers.get(0).getFirstName());
        assertEquals(1L, responseUsers.get(0).getAccountNumber());
        assertEquals(AccountStatus.ENABLED.toString(), responseUsers.get(0).getAccountStatus());
    }

    @Test
    public void testGetTransactionsBetweenDates() {
        // create transactions to be returned by the repository
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction();
        transaction1.setTid(1L);
        transaction1.setAmount(1000.0);
        transaction1.setDate(Date.valueOf("2021-01-01"));
        transaction1.setFud(user1);
        transaction1.setTud(user2);

        Transaction transaction2 = new Transaction();
        transaction2.setTid(2L);
        transaction2.setAmount(2000.0);
        transaction2.setDate(Date.valueOf("2021-02-01"));
        transaction2.setFud(user2);
        transaction2.setTud(user1);

        transactions.add(transaction1);
        transactions.add(transaction2);

        // mock the transaction repository to return the list of transactions
        when(transactionRepository.findAllBetweenDates(Date.valueOf("2021-01-01"), Date.valueOf("2021-02-28"))).thenReturn(transactions);

        // call the method being tested
        List<ResponseTransaction> responseTransactions = adminService.getTransactionsBetweenDates(Date.valueOf("2021-01-01"), Date.valueOf("2021-02-28"));

        // assert that the response contains the expected number of transactions
        assertEquals(2, responseTransactions.size());

        // assert that the response contains the expected transactions
        assertEquals(1L, responseTransactions.get(0).getTid());
        assertEquals(1000.0, responseTransactions.get(0).getAmount());
        assertEquals(2L, responseTransactions.get(1).getTid());
        assertEquals(2000.0, responseTransactions.get(1).getAmount());
    }
}