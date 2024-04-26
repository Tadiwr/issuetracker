package com.codapt.issuetracker.shared.types;

import com.codapt.issuetracker.shared.enums.IssueStatus;

import lombok.Data;

@Data
public class UpdateIssueStatusRequest {
    private IssueStatus status;    
}
