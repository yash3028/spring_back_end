package com.example.back_end.JWTUtils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.back_end.Entites.User;
import com.example.back_end.Repository.user_repo;

import jakarta.servlet.http.HttpServletRequest;
@Component
public class Validation {
    @Autowired
    private Utils jwtUtils;
    @Autowired
    private user_repo userRepo;
    public User validate(HttpServletRequest request) throws Exception{
        String authHeader = request.getHeader("Authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer")){
            throw new Exception("missing bearer or header");
        }
        String token = authHeader.substring(7);
        DecodedJWT jwt = jwtUtils.verifyToken(token);
        long id = jwt.getClaim("userId").asLong();
        
        return userRepo.findById(id).orElseThrow();

    }
     public long extractId(HttpServletRequest request) throws Exception{
        String authHeader = request.getHeader("Authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer")){
            throw new Exception("missing bearer or header");
        }
        String token = authHeader.substring(7);
        DecodedJWT jwt = jwtUtils.verifyToken(token);
        long id = jwt.getClaim("userId").asLong();
        
        return id;

    }
}
