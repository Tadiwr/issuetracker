package com.codapt.issuetracker.features.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codapt.issuetracker.shared.providers.HashProvider;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepo;

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    /** Finds a user by taking in their email and plain text password */
    public Optional<User> findByEmailAndPassword(String email, String password) {
        Optional<User> user = userRepo.findByEmail(email);

        if (user.isPresent()) {
            System.out.println("THERE IS A USER");
            String dbPasswd = user.get().getHashedPassword();
        
            if(HashProvider.checkHash(password, dbPasswd)) {
                System.out.println("Yes Correct password");
                User realUser = user.get();
                return Optional.ofNullable(realUser);
            }
        }

        return Optional.empty();
    }

    /** Saves a user with the password already hashed */
    public User save(User user) {
        return userRepo.save(user);
    }

    public User hashPsswdAndSave(User user) {
        user.setHashedPassword(HashProvider.hash(user.getHashedPassword()));
        return userRepo.save(user);
    }

}