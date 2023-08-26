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

    public List<ResponseUserProfile> getAllUsers() {
        List<ResponseUserProfile> users = userRepository.findAll().stream().collect(ArrayList::new, (list, user) -> {
            list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
        }, ArrayList::addAll);
        return users;
    }

    public List<ResponseTransaction> getAllTransactions() {
        List<ResponseTransaction> transactions = transactionRepository.findAll().stream().collect(ArrayList::new,
                (list, transaction) -> {
                    list.add(HelperFunctions.getResponseTransactionfromTransaction(transaction));
                }, ArrayList::addAll);
        return transactions;
    }

    public List<ResponseBeneficiary> getAllBeneficiaries() {
        List<ResponseBeneficiary> users = beneficiaryRepository.findAll().stream().collect(ArrayList::new,
                (list, user) -> {
                    list.add(HelperFunctions.getResponseBeneficiaryfromPayee(user));
                }, ArrayList::addAll);
        return users;
    }

    public boolean loginAdmin(UserLoginDto uld) {
        if (uld.getUserid().equals("admin") && uld.getPassword().equals("admin")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateAccountStatus(Long accountNumber, String status) {
        return userService.updateAccountStatus(accountNumber, status);
    }

    public List<ResponseUserProfile> customerFirstNameSearch(CustomerSearchDto entity) {
        List<ResponseUserProfile> users = userRepository.findByFirstName(entity.getInput()).stream()
                .collect(ArrayList::new, (list, user) -> {
                    list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
                }, ArrayList::addAll);
        return users;
    }

    public List<ResponseUserProfile> customerSearchByAccountStatus(CustomerSearchDto entity) {
        List<ResponseUserProfile> users = userRepository.findByAccountStatus(AccountStatus.valueOf(entity.getInput()))
                .stream()
                .collect(ArrayList::new, (list, user) -> {
                    list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
                }, ArrayList::addAll);
        return users;
    }

    public List<ResponseTransaction> getTransactionsBetweenDates(Date startDate, Date endDate) {
        List<ResponseTransaction> transactions = new ArrayList<>();
        transactionRepository.findAllBetweenDates(startDate, endDate).forEach(transaction -> {
            transactions.add(HelperFunctions.getResponseTransactionfromTransaction(transaction));
        });
        return transactions;
    }

}
