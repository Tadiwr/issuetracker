package com.codapt.issuetracker.features.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codapt.issuetracker.features.auth.providers.AuthProvider;
import com.codapt.issuetracker.features.users.User;
import com.codapt.issuetracker.features.users.UserService;
import com.codapt.issuetracker.shared.exceptions.UserAlreadyExistsException;
import com.codapt.issuetracker.shared.exceptions.UserNotFoundException;
import com.codapt.issuetracker.shared.types.AuthRepsonse;
import com.codapt.issuetracker.shared.types.AuthRequest;
import com.codapt.issuetracker.shared.types.CreateUserRequest;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthProvider authProvider;

    /** Authenticates a user from their credidentials passed
     * from the auth request object
     * @throws UserNotFoundException */
    public AuthRepsonse authenticate(AuthRequest request) throws UserNotFoundException {
        return authenticate(request.getEmail(), request.getPassword());
    }

    /** Authenticates a user using their email and plain text password 
     * @throws UserNotFoundException */
    public AuthRepsonse authenticate(String email, String password) throws UserNotFoundException {
        AuthRepsonse res = new AuthRepsonse();
        Optional<User> user = userService.findByEmailAndPassword(email, password);

        if(user.isEmpty()) {
            System.out.println("THERE IS NO USER");
            throw new UserNotFoundException();
        } 

        res.setToken(authProvider.issueToken(user.get()));
        return res;

    }

    public AuthRepsonse createNewUser(CreateUserRequest request) throws UserAlreadyExistsException {
        AuthRepsonse res = new AuthRepsonse();

        if (userAlreadyExists(request.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        User user = request.toUser();
        user = userService.hashPsswdAndSave(user);
        res.setToken(authProvider.issueToken(user));

        return res;
    }

    public boolean verifyToken(String token) {
        return authProvider.verifyToken(token);
    }

    public Optional<User> getTokenBearer(String token) {

        try {
            String email = authProvider.getTokenSubject(token);
            Optional<User> userOptional = userService.findByEmail(email);
            return userOptional;
            
        } catch (Exception e) {
            return Optional.empty();
        }


    }

    private boolean userAlreadyExists(String email)  {
        return userService.findByEmail(email).isPresent();
    }

}
