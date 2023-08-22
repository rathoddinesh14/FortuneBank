package com.fortunebank.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fortunebank.user.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
