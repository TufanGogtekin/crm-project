package com.tufangogtekin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tufangogtekin.model.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

}
