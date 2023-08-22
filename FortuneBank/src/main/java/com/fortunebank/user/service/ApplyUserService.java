package com.fortunebank.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.model.Address;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.AddressRepository;
import com.fortunebank.user.repository.ApplyUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ApplyUserService {
    @Autowired
    private ApplyUserRepository applyUserRepository;

    @Autowired
    private AddressRepository addressRepository;

    public UserDetails saveUserDetails(UserDetails userDetails) {
        return applyUserRepository.save(userDetails);
    }

    public void saveAddress(Address tAddress) {
        addressRepository.save(tAddress);
    }
}
