package com.example.back_end.JWTUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
@Component
public class Validation {
    @Autowired
    private Utils jwtUtils;

    public DecodedJWT validate(HttpServletRequest request) throws Exception{
        String authHeader = request.getHeader("Authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer")){
            throw new Exception("missing bearer or header");
        }
        String token = authHeader.substring(7);
        return jwtUtils.verifyToken(token);
    }
}
