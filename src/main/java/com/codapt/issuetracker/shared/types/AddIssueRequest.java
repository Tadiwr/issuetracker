package com.codapt.issuetracker.shared.types;

import com.codapt.issuetracker.shared.enums.IssueStatus;

import lombok.Data;

@Data
public class AddIssueRequest {
    
    private String description;
    private IssueStatus status;
    
// public AddIssueRequest() {
    //     this.status = IssueStatus.QUEUED;
    // }

    
    
}
