package com.example.back_end.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.back_end.Entites.User;
import com.example.back_end.Models.Credentials;
import com.example.back_end.Services.user_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class User_controller {
    @Autowired
    private user_services user_service;

    @PostMapping("/save_user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(user_service.save_user(user));
    }

    // private Credentials cred;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credentials cred) {
        String result = user_service.login(cred);
        if ("success".equals(result)) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(401).body("unauthorized");
        }

    }
}
