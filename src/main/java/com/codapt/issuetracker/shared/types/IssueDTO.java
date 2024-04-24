package com.codapt.issuetracker.shared.types;

import java.time.LocalDateTime;

import com.codapt.issuetracker.features.issue.Issue;
import com.codapt.issuetracker.features.users.User;
import com.codapt.issuetracker.shared.enums.IssueStatus;

import lombok.Data;

@Data
public class IssueDTO {
    
    private Long Id;
    private String description;
    private IssueStatus status;
    private LocalDateTime createdAt;
    private UserDTO createdBy;

    public static IssueDTO fromIssue(Issue  issue) {
        IssueDTO dto = new IssueDTO();

        dto.setId(issue.getId());
        dto.setDescription(issue.getDescription());
        dto.setCreatedAt(issue.getCreatedAt());
        dto.setCreatedBy(UserDTO.fromUser(issue.getCreatedBy()));
        dto.setStatus(issue.getStatus());

        return dto;
    } 

}
