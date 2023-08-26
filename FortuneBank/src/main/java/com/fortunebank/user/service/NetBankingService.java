package com.fortunebank.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.dto.ForgotPasswordDto;
import com.fortunebank.user.dto.ForgotUserIdDto;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.repository.NetBankingUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NetBankingService {

    @Autowired
    private NetBankingUserRepository netBankingUserRepository;

    public NetBankingUser registerNetBankingUser(NetBankingUser netBankingUser) {
        return netBankingUserRepository.save(netBankingUser);
    }

    public Optional<NetBankingUser> loginGetUser(String userId) {
        return netBankingUserRepository.findByUserId(userId);
    }

    public boolean resetPassword(ForgotPasswordDto fpd) throws Exception {
        NetBankingUser user = netBankingUserRepository.findByUserId(fpd.getUserid())
                .orElseThrow(() -> new Exception(""));
        user.setLoginPassword(fpd.getPassword());
        return netBankingUserRepository.save(user) != null ? true : false;
    }

    public String getUserId(ForgotUserIdDto fud) throws Exception {
        NetBankingUser user = netBankingUserRepository.findByAccountNumber(fud.getAccountnumber())
                .orElseThrow(() -> new Exception(""));
        return user.getUserId();
    }

    public Long getAccountNumber(Long userid) throws Exception {

        NetBankingUser user = netBankingUserRepository.findById(userid)
                .orElseThrow(() -> new Exception(""));
        return user.getAccountNumber();
    }
}
