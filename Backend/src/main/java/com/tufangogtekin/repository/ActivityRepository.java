package com.tufangogtekin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tufangogtekin.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
