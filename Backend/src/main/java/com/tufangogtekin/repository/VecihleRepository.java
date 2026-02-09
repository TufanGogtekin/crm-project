package com.tufangogtekin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tufangogtekin.model.Vecihle;

@Repository
public interface VecihleRepository extends JpaRepository<Vecihle, Long> {

}
