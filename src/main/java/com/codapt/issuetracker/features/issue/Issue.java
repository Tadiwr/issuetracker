package com.codapt.issuetracker.features.issue;

import java.time.LocalDateTime;

import com.codapt.issuetracker.features.users.User;
import com.codapt.issuetracker.shared.enums.IssueStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "issues")
public class Issue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String description;

    @Column(nullable = false)
    private IssueStatus status = IssueStatus.QUEUED;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="created_by")
    private User createdBy;

}
