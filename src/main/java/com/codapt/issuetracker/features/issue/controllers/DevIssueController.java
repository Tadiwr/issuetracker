package com.codapt.issuetracker.features.issue.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codapt.issuetracker.features.issue.Issue;
import com.codapt.issuetracker.features.issue.IssueService;
import com.codapt.issuetracker.features.users.User;
import com.codapt.issuetracker.shared.types.AddIssueRequest;
import com.codapt.issuetracker.shared.types.IssueDTO;

import jakarta.websocket.server.PathParam;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/dev/issues")
public class DevIssueController {
    
    @Autowired
    private IssueService issueService;

    @GetMapping()
    public List<IssueDTO> getAllIssues(@RequestParam(required = false) String desc) {

        if (desc == null) {
            return issueService.getAllIssuesDTO();
        }
        return issueService.toDTO(issueService.getIssuesLike(desc));

    }


    @PostMapping("/add")
    public ResponseEntity<IssueDTO> addIssue(@RequestBody AddIssueRequest body, @RequestAttribute User user) { 
        Issue issue = issueService.addIssue(body, user);
        return ResponseEntity.ok(IssueDTO.fromIssue(issue));
    }

    
    

}
