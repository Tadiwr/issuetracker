package com.codapt.issuetracker.shared.types;

import com.codapt.issuetracker.features.users.User;
import com.codapt.issuetracker.shared.enums.UserRole;
import com.codapt.issuetracker.shared.providers.HashProvider;

import lombok.Data;

@Data
public class CreateUserRequest {
    
    private String name;
    private String email;
    private String password;
    private UserRole role;

    public User toUser() {

        User user = new User();
        String hashedPassword = HashProvider.hashString(this.getPassword());

        user.setName(this.getName());
        user.setEmail(this.getEmail());
        user.setHashedPassword(hashedPassword);
        user.setRole(this.getRole());

        return user;
    }

}
