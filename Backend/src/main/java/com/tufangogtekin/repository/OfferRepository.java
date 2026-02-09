package com.tufangogtekin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tufangogtekin.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{

}
