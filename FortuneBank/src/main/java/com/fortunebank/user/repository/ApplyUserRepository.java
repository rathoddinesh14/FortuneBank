package com.fortunebank.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fortunebank.user.model.UserDetails;

public interface ApplyUserRepository extends JpaRepository<UserDetails, Long> {

}
