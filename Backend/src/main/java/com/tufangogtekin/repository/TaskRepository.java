package com.tufangogtekin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tufangogtekin.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
