package com.fortunebank.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.repository.NetBankingUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NetBankingUserRegistrationService {

    @Autowired
    private NetBankingUserRepository netBankingUserRepository;

    public NetBankingUser registerNetBankingUser(NetBankingUser netBankingUser) {
        return netBankingUserRepository.save(netBankingUser);
    }
    
    public Optional<NetBankingUser> loginGetUser(String userId) {
    	return netBankingUserRepository.findByUserId(userId);
    }
}
