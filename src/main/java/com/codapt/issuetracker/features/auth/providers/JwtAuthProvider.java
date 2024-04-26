package com.codapt.issuetracker.features.auth.providers;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codapt.issuetracker.features.users.User;
import com.codapt.issuetracker.shared.providers.JwtProvider;

@Service
public class JwtAuthProvider implements AuthProvider {

    @Autowired
    private JwtProvider jwtUtil;

    @Value("${jwt.secret.key}")
    private String secretKeyStr;

    public String issueToken(User user) {

        String sub = user.getEmail();
        Map<String, Object> claims = generateClaims(user);
        SecretKey key = jwtUtil.secretKeyFromStr(secretKeyStr);
        Long expireAfter = jwtUtil.daysToMilli(1);

        return jwtUtil.generateToken(sub, claims, key, expireAfter);
    }

    /** Generates a HashMap of key value pairs representing 
     * claims to a user object */
    private Map<String, Object> generateClaims(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.getId());
        claims.put("role", user.getRole());

        return claims;
    }

    public boolean verifyToken(String token) {

        try {
            jwtUtil.verify(token, secretKeyStr);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public String getTokenSubject(String token) {
        return jwtUtil.getSubject(token, secretKeyStr);
    }
    
}
