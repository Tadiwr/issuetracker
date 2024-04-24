package com.codapt.issuetracker.shared.types;

import com.codapt.issuetracker.features.users.User;
import com.codapt.issuetracker.shared.enums.UserRole;

import lombok.Data;

@Data
public class UserDTO {
    
    private String name;
    private String email;
    private UserRole role;
    
    public static UserDTO fromUser(User user) {
        UserDTO dto = new UserDTO();

        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        return dto;
    } 

}
