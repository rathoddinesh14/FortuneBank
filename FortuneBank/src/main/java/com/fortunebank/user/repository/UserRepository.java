package com.fortunebank.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fortunebank.user.model.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByAccountNumber(Long accountNumber);
}
