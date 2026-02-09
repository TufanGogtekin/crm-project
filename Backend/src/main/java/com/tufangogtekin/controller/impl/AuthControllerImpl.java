package com.tufangogtekin.controller.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tufangogtekin.model.User;
import com.tufangogtekin.repository.UserRepository;

@RestController
@RequestMapping("/rest/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthControllerImpl {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User login(@RequestBody User loginRequest) {
        Optional<User> user = userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        return user.orElse(null);
    }

    @PostMapping("/register")
    public User register(@RequestBody User newUser) {
        newUser.setRole("ADMIN"); 
        return userRepository.save(newUser);
    }
}