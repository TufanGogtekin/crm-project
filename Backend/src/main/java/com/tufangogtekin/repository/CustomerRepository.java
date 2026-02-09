package com.tufangogtekin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tufangogtekin.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
