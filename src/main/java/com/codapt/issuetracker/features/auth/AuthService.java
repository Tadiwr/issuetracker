package com.codapt.issuetracker.features.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codapt.issuetracker.features.users.User;
import com.codapt.issuetracker.features.users.UsersRepository;
import com.codapt.issuetracker.shared.types.AuthRepsonse;
import com.codapt.issuetracker.shared.types.AuthRequest;
import com.codapt.issuetracker.shared.types.CreateUserRequest;

@Service
public class AuthService {

    @Autowired
    private UsersRepository userRepo;

    /** Authenticates a user from their credidentials passed
     * from the auth request object*/
    public AuthRepsonse authenticate(AuthRequest request) {
        AuthRepsonse res = new AuthRepsonse();

        return res;
    }

    /** Authenticates a user using their email and plain text password */
    public AuthRepsonse authenticate(String email, String password) {
        AuthRepsonse res = new AuthRepsonse();

        return res;
    }

    public AuthRepsonse createNewUser(CreateUserRequest request) {
        User user = request.toUser();
        userRepo.save(user);


        // NOTE: The method authenticate accepts a plain text password
        return authenticate(user.getEmail(), request.getPassword());
    }

    public boolean verifyToken(String token) {
        return true;
    }

}
