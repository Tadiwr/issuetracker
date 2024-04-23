package com.codapt.issuetracker.features.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codapt.issuetracker.shared.exceptions.UserNotFoundException;
import com.codapt.issuetracker.shared.types.AuthRepsonse;
import com.codapt.issuetracker.shared.types.AuthRequest;
import com.codapt.issuetracker.shared.types.CreateUserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthRepsonse> login(@RequestBody AuthRequest req) {

        try {
            AuthRepsonse res = authService.authenticate(req);
            return ResponseEntity.ok(res);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/create/account")
    public ResponseEntity<AuthRepsonse> createAccount(@RequestBody CreateUserRequest req) {
        AuthRepsonse res = authService.createNewUser(req);

        return ResponseEntity.ok(res);
    }
        
}
