package com.tufangogtekin.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tufangogtekin.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
}