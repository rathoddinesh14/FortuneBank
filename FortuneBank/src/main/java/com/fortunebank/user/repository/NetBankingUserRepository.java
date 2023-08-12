package com.fortunebank.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fortunebank.user.model.NetBankingUser;

public interface NetBankingUserRepository extends JpaRepository<NetBankingUser, Long> {

}
