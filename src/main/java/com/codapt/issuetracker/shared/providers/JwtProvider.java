package com.codapt.issuetracker.shared.providers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.KeyAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecretKeyAlgorithm;

/** Handles generating tokens, parsing and getting claims */

@Service
public class JwtProvider {

    private Long milliSecondsInOneSecond = 1000L;

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
        // byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        // return new SecretKeySpec(bytes, "AES");

        byte[] decodedKey = Base64.getDecoder().decode(str);
        return Keys.hmacShaKeyFor(decodedKey);
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

    /** Gets the subject of a key from the token using the secret key */
    public String getSubject(String token, SecretKey key) {
        Claims claims = getClaims(token, key);
        return claims.getSubject(); 
    }

    /** Gets the subject of a key from the token using the secret key */
    public String getSubject(String token, String key) {
        Claims claims = getClaims(token, key);
        return claims.getSubject(); 
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

    /* Returns the number of milliseconds in n number of seconds */
    public Long secondsToMilli(int seconds) {
        return milliSecondsInOneSecond * seconds;
    }

    /* Returns the number of milliseconds in n number of minutes */
    public Long minutesToMills(int minutes) {
        return secondsToMilli(60) * minutes;
    }

    /* Returns the number of milliseconds in n number of hours */
    public Long hoursToMillis(int hours) {
        return minutesToMills(60) * hours;
    }

    /* Returns the number of milliseconds in n number of days */
    public Long daysToMilli(int days) {
        return hoursToMillis(24) * days;
    }

    /* Returns the number of milliseconds in n number of years */
    public Long yearsToMillis(int years) {
        return daysToMilli(365) * years;
    } 

}
