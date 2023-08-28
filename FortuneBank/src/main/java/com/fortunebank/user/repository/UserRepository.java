package com.fortunebank.user.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fortunebank.user.enumtype.AccountStatus;
import com.fortunebank.user.model.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByAccountNumber(Long accountNumber);

    Collection<UserDetails> findByFirstName(String input);

    Collection<UserDetails> findByLastName(String input);

    Collection<UserDetails> findByMiddleName(String input);

    Collection<UserDetails> findByFatherName(String input);

    Collection<UserDetails> findByPhone(String input);

    Collection<UserDetails> findByEmail(String input);

    Collection<UserDetails> findByAadharNumber(String input);

    Collection<UserDetails> findByAccountStatus(AccountStatus accountStatus);

}
