package com.codapt.issuetracker.shared.exceptions;

public class UserNotFoundException extends Exception {
    
    @Override
    public String getMessage() {
        return "User was not found";
    }

}
