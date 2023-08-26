package com.fortunebank.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.enumtype.AccountStatus;
import com.fortunebank.user.model.Address;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.AddressRepository;
import com.fortunebank.user.repository.UserRepository;
import com.fortunebank.user.utils.HelperFunctions;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public UserDetails saveUserDetails(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }

    public void saveAddress(Address tAddress) {
        addressRepository.save(tAddress);
    }

    public ResponseUserProfile getUserProfile(Long accountNumber) {
        Optional<UserDetails> userDetails = userRepository.findByAccountNumber(accountNumber);

        userDetails.orElseThrow(() -> new RuntimeException("User not found"));

        return HelperFunctions.getResponseUserProfilefromUserDetails(userDetails.get());
    }

    public String getName(Long accountNumber) {
        return userRepository.findByAccountNumber(accountNumber).get().getFirstName();
    }

    public boolean updateAccountStatus(Long accountNumber, String status) {
        Optional<UserDetails> userDetails = userRepository.findByAccountNumber(accountNumber);
        userDetails.orElseThrow(() -> new RuntimeException("User not found"));
        userDetails.get().setAccountStatus(AccountStatus.valueOf(status));
        userRepository.save(userDetails.get());
        return true;
    }
}
