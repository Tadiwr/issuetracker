package com.codapt.issuetracker.shared.providers;

import org.mindrot.jbcrypt.BCrypt;

/** Provides APIs for hashing and comparing hashed passwords */
public class HashProvider {
    
    /** Takes a plain string and hashes it */
    public static String hash(String plainStr) {
        return BCrypt.hashpw(plainStr, BCrypt.gensalt());
    }

    /** Compares if the hash of the plain string is equal to the hashed target string */
    public static boolean checkHash(String plainStr, String hashed) {
        return BCrypt.checkpw(plainStr, hashed);
    }

}
