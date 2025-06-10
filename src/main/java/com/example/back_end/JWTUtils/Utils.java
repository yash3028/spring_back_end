package com.example.back_end.JWTUtils;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class Utils {
    private static final String SECRET_KEY = "yashwanth";

    public String generateToken(String mobile, String email, long userId) throws UnsupportedEncodingException {
        try {
            return JWT.create()
                    .withClaim("mobile", mobile)
                    .withClaim("email", email)
                    .withClaim("userId", userId)
                    .sign(Algorithm.HMAC256(SECRET_KEY));
        } catch (JWTCreationException | IllegalArgumentException e) {
            throw new RuntimeException("Error while generating JWT token", e);
        }
    }
    public DecodedJWT verifyToken(String token) throws IllegalArgumentException, UnsupportedEncodingException{
        try{
            return JWT.require(Algorithm.HMAC256(SECRET_KEY))
          .build()
          .verify(token);
    }
    catch(JWTVerificationException e){
            throw new RuntimeException("Invalid or expired token", e);
    }
}
}
