package com.codapt.issuetracker.features.users;

import java.util.List;

import com.codapt.issuetracker.features.issue.Issue;
import com.codapt.issuetracker.shared.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hashedPassword;

    @Column(nullable = false)
    private UserRole role = UserRole.DEVELOPER;

    @OneToMany(mappedBy = "createdBy")
    private List<Issue> issues;

    
}
