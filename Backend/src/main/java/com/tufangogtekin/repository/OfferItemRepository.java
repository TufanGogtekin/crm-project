package com.tufangogtekin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tufangogtekin.model.OfferItem;

@Repository
public interface OfferItemRepository extends JpaRepository<OfferItem, Long> {

}
