package com.fortunebank.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.model.Address;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.AddressRepository;
import com.fortunebank.user.repository.UserRepository;

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
        ResponseUserProfile responseUserProfile = new ResponseUserProfile();
        responseUserProfile.setAccountNumber(userDetails.get().getAccountNumber());
        responseUserProfile.setFirstName(userDetails.get().getFirstName());
        responseUserProfile.setMiddleName(userDetails.get().getMiddleName());
        responseUserProfile.setLastName(userDetails.get().getLastName());
        responseUserProfile.setFatherName(userDetails.get().getFatherName());
        responseUserProfile.setPhone(userDetails.get().getPhone());
        responseUserProfile.setEmail(userDetails.get().getEmail());
        responseUserProfile.setAadharNumber(userDetails.get().getAadharNumber());
        responseUserProfile.setDob(userDetails.get().getDob().toString());
        responseUserProfile.setAccountType(userDetails.get().getAccountType());
        responseUserProfile.setBalance(userDetails.get().getBalance());

        return responseUserProfile;
    }
}
