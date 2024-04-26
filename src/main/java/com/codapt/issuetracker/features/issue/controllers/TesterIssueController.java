package com.codapt.issuetracker.features.issue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codapt.issuetracker.features.issue.IssueService;
import com.codapt.issuetracker.shared.exceptions.ResourceNotFound;
import com.codapt.issuetracker.shared.types.IssueDTO;
import com.codapt.issuetracker.shared.types.UpdateIssueStatusRequest;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/tester/issues")
public class TesterIssueController {
    
    @Autowired
    private IssueService issueService;

    @DeleteMapping("/delete")
    public void deleteIssue(@RequestParam Long id) {
        issueService.deleteIssue(id);
    }

    @PutMapping("/status/update/{id}")
    public ResponseEntity<IssueDTO> updateIssueStatus(@PathVariable Long id, @RequestBody UpdateIssueStatusRequest body) {
        issueService.changeIssueStatusTo(id, body);

        try {
            return ResponseEntity.ok(issueService.getIssueByIdDTO(id));
        } catch (ResourceNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }
}
