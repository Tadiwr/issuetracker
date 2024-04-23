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

    /** Finds a user by taking in their email and plain text password */
    public Optional<User> findByEmailAndPassword(String email, String password) {
        String hashedPassword = hashPassword(password);

        return userRepo.findByEmailAndHashedPassword(email, hashedPassword);
    }

    /** Saves a user with the password already hashed */
    public User save(User user) {
        return userRepo.save(user);
    }

    /** Takes in a user object with a plaintext password,
     * hashes it and then saves it
     */
    public User hashPasswordAndSave(User user) {
        user.setHashedPassword(hashPassword(user.getHashedPassword()));
        return save(user);
    }

    private String hashPassword(String plain) {
        String hashedPassword = HashProvider.hashString(plain);
        return hashedPassword;
    }

}