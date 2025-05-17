package com.example.back_end.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.back_end.Entites.User;
import com.example.back_end.Models.Credentials;
import com.example.back_end.Repository.user_repo;
import com.example.back_end.Services.user_services;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5175")

public class User_controller {
    @Autowired
    private user_services user_service;
    @Autowired
    private user_repo userRepo;

    @PostMapping("/save_user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        Optional<User> existingUser = userRepo.findByMobile(user.getMobile());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(409).body(null); // Conflict: User already exists
        }
        return ResponseEntity.ok(user_service.save_user(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Credentials cred)
            throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
        Map<String, Object> result = user_service.login(cred);
        if ("success".equals(result.get("message"))) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(401).body(result);
        }
    }
}
