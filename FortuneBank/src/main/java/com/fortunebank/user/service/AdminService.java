package com.fortunebank.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.dto.CustomerSearchDto;
import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.dto.UserLoginDto;
import com.fortunebank.user.enumtype.AccountStatus;
import com.fortunebank.user.exception.InvalidLoginException;
import com.fortunebank.user.repository.PayeeRepository;
import com.fortunebank.user.repository.TransactionRepository;
import com.fortunebank.user.repository.UserRepository;
import com.fortunebank.user.utils.HelperFunctions;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PayeeRepository beneficiaryRepository;

    @Autowired
    private UserService userService;

    /**
     * This method is used to get all users.
     * 
     * @return List<ResponseUserProfile>
     */
    public List<ResponseUserProfile> getAllUsers() {
        List<ResponseUserProfile> users = userRepository.findAll().stream().collect(ArrayList::new, (list, user) -> {
            list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
        }, ArrayList::addAll);
        return users;
    }

    /**
     * This method is used to get all transactions.
     * 
     * @return List<ResponseTransaction>
     */
    public List<ResponseTransaction> getAllTransactions() {
        List<ResponseTransaction> transactions = transactionRepository.findAll().stream().collect(ArrayList::new,
                (list, transaction) -> {
                    list.add(HelperFunctions.getResponseTransactionfromTransaction(transaction));
                }, ArrayList::addAll);
        return transactions;
    }

    /**
     * This method is used to get all beneficiaries.
     * 
     * @return List<ResponseBeneficiary>
     */
    public List<ResponseBeneficiary> getAllBeneficiaries() {
        List<ResponseBeneficiary> users = beneficiaryRepository.findAll().stream().collect(ArrayList::new,
                (list, user) -> {
                    list.add(HelperFunctions.getResponseBeneficiaryfromPayee(user));
                }, ArrayList::addAll);
        return users;
    }

    /**
     * This method is used to login admin.
     * 
     * @param UserLoginDto uld
     * @return boolean
     * @throws InvalidLoginException
     */
    public boolean loginAdmin(UserLoginDto uld) {
        if (uld.getUserid().equals("admin") && uld.getPassword().equals("admin")) {
            return true;
        } else {
            throw new InvalidLoginException();
        }
    }

    /**
     * This method is used to update account status of a user.
     * 
     * @param Long   accountNumber
     * @param String status
     * @return boolean
     */
    public boolean updateAccountStatus(Long accountNumber, String status) {
        return userService.updateAccountStatus(accountNumber, AccountStatus.valueOf(status));
    }

    /**
     * This method is used to search a user by account number.
     * 
     * @param CustomerSearchDto entity
     * @return List<ResponseUserProfile>
     */
    public List<ResponseUserProfile> customerAccountNumberSearch(CustomerSearchDto entity) {
        List<ResponseUserProfile> users = new ArrayList<>();
        userRepository.findByAccountNumber(Long.parseLong(entity.getInput())).ifPresent(user -> {
            users.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
        });
        return users;
    }

    /**
     * This method is used to search a user by account number.
     * 
     * @param CustomerSearchDto entity
     * @return List<ResponseUserProfile>
     */
    public List<ResponseUserProfile> customerFirstNameSearch(CustomerSearchDto entity) {
        List<ResponseUserProfile> users = userRepository.findByFirstName(entity.getInput()).stream()
                .collect(ArrayList::new, (list, user) -> {
                    list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
                }, ArrayList::addAll);
        return users;
    }

    /**
     * This method is used to search a user by middle name.
     * 
     * @param CustomerSearchDto entity
     * @return List<ResponseUserProfile>
     */
    public List<ResponseUserProfile> customerMiddleNameSearch(CustomerSearchDto entity) {
        List<ResponseUserProfile> users = userRepository.findByMiddleName(entity.getInput()).stream()
                .collect(ArrayList::new, (list, user) -> {
                    list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
                }, ArrayList::addAll);
        return users;
    }

    /**
     * This method is used to search a user by last name.
     * 
     * @param CustomerSearchDto entity
     * @return List<ResponseUserProfile>
     */
    public List<ResponseUserProfile> customerLastNameSearch(CustomerSearchDto entity) {
        List<ResponseUserProfile> users = userRepository.findByLastName(entity.getInput()).stream()
                .collect(ArrayList::new, (list, user) -> {
                    list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
                }, ArrayList::addAll);
        return users;
    }

    /**
     * This method is used to search a user by phone number.
     * 
     * @param CustomerSearchDto entity
     * @return List<ResponseUserProfile>
     */
    public List<ResponseUserProfile> customerPhoneSearch(CustomerSearchDto entity) {
        List<ResponseUserProfile> users = userRepository.findByPhone(entity.getInput()).stream()
                .collect(ArrayList::new, (list, user) -> {
                    list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
                }, ArrayList::addAll);
        return users;
    }

    /**
     * This method is used to search a user by father name.
     * 
     * @param CustomerSearchDto entity
     * @return List<ResponseUserProfile>
     */
    public List<ResponseUserProfile> customerFatherNameSearch(CustomerSearchDto entity) {
        List<ResponseUserProfile> users = userRepository.findByFatherName(entity.getInput()).stream()
                .collect(ArrayList::new, (list, user) -> {
                    list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
                }, ArrayList::addAll);
        return users;
    }

    /**
     * This method is used to search a user by email.
     * 
     * @param CustomerSearchDto entity
     * @return List<ResponseUserProfile>
     */
    public List<ResponseUserProfile> customerEmailSearch(CustomerSearchDto entity) {
        List<ResponseUserProfile> users = userRepository.findByEmail(entity.getInput()).stream()
                .collect(ArrayList::new, (list, user) -> {
                    list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
                }, ArrayList::addAll);
        return users;
    }

    /**
     * This method is used to search a user by aadhar number.
     * 
     * @param CustomerSearchDto entity
     * @return List<ResponseUserProfile>
     */
    public List<ResponseUserProfile> customerAadharNumberSearch(CustomerSearchDto entity) {
        List<ResponseUserProfile> users = userRepository.findByAadharNumber(entity.getInput()).stream()
                .collect(ArrayList::new, (list, user) -> {
                    list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
                }, ArrayList::addAll);
        return users;
    }

    /**
     * This method is used to search a user by account status.
     * 
     * @param CustomerSearchDto entity
     * @return List<ResponseUserProfile>
     */
    public List<ResponseUserProfile> customerSearchByAccountStatus(CustomerSearchDto entity) {
        List<ResponseUserProfile> users = userRepository.findByAccountStatus(AccountStatus.valueOf(entity.getInput()))
                .stream()
                .collect(ArrayList::new, (list, user) -> {
                    list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
                }, ArrayList::addAll);
        return users;
    }

    /**
     * This method is used to get transactions between dates.
     * 
     * @param Date startDate
     * @param Date endDate
     * @return List<ResponseTransaction>
     */
    public List<ResponseTransaction> getTransactionsBetweenDates(Date startDate, Date endDate) {
        List<ResponseTransaction> transactions = new ArrayList<>();
        transactionRepository.findAllBetweenDates(startDate, endDate).forEach(transaction -> {
            transactions.add(HelperFunctions.getResponseTransactionfromTransaction(transaction));
        });
        return transactions;
    }

}
