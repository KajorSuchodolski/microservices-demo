package com.example.microservicesdemo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

public class JwtUtil {

    private static final String SECRET = "CxJg-jun3gY4CuLcNBtM1Fl9BqbsyeWglTymfgIq-5A";
    private static final long EXPIRATION_TIME = 30 * 60 * 1000; // 30 minutes

    public static String generateToken(String login, Set<String> roles) {

        Key signInKey = new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(login)
                .claim("roles", roles)
                .setIssuer("account-microservice")
                .setExpiration(new Date(new Date().getTime() + EXPIRATION_TIME))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(signInKey)
                .compact();
    }
}
