package com.codapt.issuetracker.shared.providers;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/** Handles generating tokens, parsing and getting claims */

@Service
public class JwtProvider {
    
    @Value("${jwt.secret.key}")
    private String secretKeyStr;

    /** Generate a token that never expires */
    public String generateToken(String sub, Map<String, Object> claims, SecretKey key) {
        
        return Jwts.builder()
            .subject(sub)
            .claims(claims)
            .signWith(key)
            .issuedAt(null)
            .issuer("issuetracker")
            .compact();
    }

    /** Generate a token that never expires */
    public String generateToken(String sub, Map<String, Object> claims, String key) {
        
        return Jwts.builder()
            .subject(sub)
            .claims(claims)
            .signWith(secretKeyFromStr(key))
            .issuedAt(null)
            .issuer("issuetracker")
            .compact();
    }

    /* Generate a token with a fixed expiration time */
    public String generateToken(String sub, Map<String, Object> claims, SecretKey key, Long expireAfter) {
        
        /** The exact time stamp in which the token will expire */
        long expiryTimeStamp = System.currentTimeMillis() + expireAfter;

        return Jwts.builder()
            .subject(sub)
            .claims(claims)
            .signWith(key)
            .issuedAt(null)
            .expiration(new Date(expiryTimeStamp))
            .issuer("issuetracker")
            .compact();
    }

    /* Generate a token with a fixed expiration time */
    public String generateToken(String sub, Map<String, Object> claims, String key, Long expireAfter) {
        
        /** The exact time stamp in which the token will expire */
        long expiryTimeStamp = System.currentTimeMillis() + expireAfter;

        return Jwts.builder()
            .subject(sub)
            .claims(claims)
            .signWith(secretKeyFromStr(key))
            .issuedAt(null)
            .expiration(new Date(expiryTimeStamp))
            .issuer("issuetracker")
            .compact();
    }

    public SecretKey secretKeyFromStr(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(bytes, "AES");
    }

    public Claims getClaims(String token, String key) {
        return (Claims) Jwts.parser()
        .verifyWith(secretKeyFromStr(key))
        .build().parse(token).getPayload();
    }

    public Claims getClaims(String token, SecretKey key) {
        return (Claims) Jwts.parser()
        .verifyWith(key)
        .build().parse(token)
        .getPayload(); 
    }

    /** Verifies if a token is valid based on a token and a key */
    public boolean verify(String token, String key) {
        try {

            Jwts.parser()
            .verifyWith(secretKeyFromStr(key))
            .build().parse(token);

            return true;

        } catch (Exception e) {

            return false;

        }
    }

    /** Verifies if a token is valid based on a token and a key */
    public boolean verify(String token, SecretKey key) {
        try {

            Jwts.parser()
            .verifyWith(key)
            .build().parse(token);

            return true;

        } catch (Exception e) {

            return false;

        }
    }

}
