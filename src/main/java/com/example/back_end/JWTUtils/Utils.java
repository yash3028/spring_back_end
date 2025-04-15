package com.example.back_end.JWTUtils;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Component
public class Utils {
    private static final String SECRET_KEY = "yashwanth";

    public String generateToken(String mobile, String email) throws UnsupportedEncodingException {
        try {
            return JWT.create()
                    .withClaim("mobile", mobile)
                    .withClaim("email", email)
                    .sign(Algorithm.HMAC256(SECRET_KEY));
        } catch (JWTCreationException | IllegalArgumentException e) {
            throw new RuntimeException("Error while generating JWT token", e);
        }
    }
}
