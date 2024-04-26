package com.codapt.issuetracker.shared.exceptions;

public class UserAlreadyExistsException extends Exception {
    @Override
    public String getMessage() {
        return "User with the same email already exists";
    }
}
