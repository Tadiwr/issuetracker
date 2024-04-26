package com.codapt.issuetracker.features.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codapt.issuetracker.features.users.User;
import com.codapt.issuetracker.shared.exceptions.UserAlreadyExistsException;
import com.codapt.issuetracker.shared.exceptions.UserNotFoundException;
import com.codapt.issuetracker.shared.types.AuthRepsonse;
import com.codapt.issuetracker.shared.types.AuthRequest;
import com.codapt.issuetracker.shared.types.CreateUserRequest;
import com.codapt.issuetracker.shared.types.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthRepsonse> login(@RequestBody AuthRequest req) {

        try {
            System.err.println(req);
            AuthRepsonse res = authService.authenticate(req);
            return ResponseEntity.ok(res);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/create/account")
    public ResponseEntity<AuthRepsonse> createAccount(@RequestBody CreateUserRequest req) {
        AuthRepsonse res;

        try {
            res = authService.createNewUser(req);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/logout")
    public void logout() {
        
    }

    @GetMapping("/test")
    public UserDTO testAuthentication(@RequestAttribute User user) {
        return UserDTO.fromUser(user);
    }
    
        
}
