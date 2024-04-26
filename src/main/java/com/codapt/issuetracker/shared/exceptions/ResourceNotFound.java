package com.codapt.issuetracker.shared.exceptions;

public class ResourceNotFound extends Exception {
    
    @Override
    public String getMessage() {
        return "Resource not found";
    }

}
