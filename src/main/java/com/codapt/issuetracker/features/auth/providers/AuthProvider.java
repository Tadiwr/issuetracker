package com.codapt.issuetracker.features.auth.providers;

import com.codapt.issuetracker.features.users.User;

public interface AuthProvider {
    
    /** Generates an authentication token */
    public String issueToken(User user);

    /** Verifies a token */
    public boolean verifyToken(String token);

}
