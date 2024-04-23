package com.codapt.issuetracker.shared.providers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** Provides APIs for hashing and comparing hashed passwords */
@Service
public class PasswordHashProvider {
    
    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    /** Takes a plain string and hashes it */
    public static String hashString(String plainStr) {
        return encoder.encode(plainStr);
    }

    /** Compares if the hash of the plain string is equal to the hashed target string */
    public boolean compare(String plainStr, String hashed) {
        return encoder.matches(plainStr, hashed);
    }

}
