package com.example.back_end.Services;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.back_end.Entites.User;
import com.example.back_end.Models.Credentials;
import com.example.back_end.Repository.user_repo;
import com.example.back_end.JWTUtils.*;

@Service
public class user_services {
    @Autowired
    private user_repo userRepo;

    public User save_user(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user should not be null");
        }
        return userRepo.save(user);
    }

    @Autowired
    private Utils utils;

    public Map<String, Object> login(Credentials cred) throws UnsupportedEncodingException {
        User user = userRepo.findUserBymobile(cred.getMobile());
        Map<String, Object> response = new HashMap<>();
        if (user != null && user.getPassword().equals(cred.getPassword())) {
            String token = utils.generateToken(user.getMobile(), user.getEmail());

            user.setToken(token);
            userRepo.save(user);
            response.put("message", "success");
            response.put("token", token);
        } else {
            response.put("message", "unauthorized");
            response.put("token", null);
        }
        return response;
    }

}