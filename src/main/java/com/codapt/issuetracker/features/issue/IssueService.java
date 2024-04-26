package com.codapt.issuetracker.features.issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codapt.issuetracker.features.users.User;
import com.codapt.issuetracker.shared.enums.IssueStatus;
import com.codapt.issuetracker.shared.exceptions.ResourceNotFound;
import com.codapt.issuetracker.shared.types.AddIssueRequest;
import com.codapt.issuetracker.shared.types.IssueDTO;
import com.codapt.issuetracker.shared.types.UpdateIssueStatusRequest;

import jakarta.transaction.Transactional;

@Service
public class IssueService {
    
    @Autowired
    private IssuesRepository issueRepo;


    @Transactional
    public Issue addIssue(AddIssueRequest req, User user) {

        Issue issue = new Issue();
        issue.setCreatedAt(LocalDateTime.now());
        issue.setCreatedBy(user);
        issue.setDescription(req.getDescription());

        if(req.getStatus() == null) {
            issue.setStatus(IssueStatus.QUEUED);
        } else {
            issue.setStatus(req.getStatus());
        }

        issueRepo.save(issue);

        return issue;

    }

    public List<Issue> getAllIssues() {
        return (List<Issue>) issueRepo.findAll();
    }

    public List<IssueDTO> toDTO(List<Issue> issues) {
        List<IssueDTO> issueDtos = new ArrayList<>();

        for (Issue issue : issues) {
            issueDtos.add(IssueDTO.fromIssue(issue));
        }

        return issueDtos;
    }

    public IssueDTO toDTO(Issue issue) {
        return IssueDTO.fromIssue(issue);
    }

    public List<IssueDTO> getAllIssuesDTO() {
        List<Issue> issues = (List<Issue>) issueRepo.findAll();

        return toDTO(issues);
    }

    public List<Issue> getIssuesCreatedBy(User user) {
        return issueRepo.findByCreatedBy(user);
    }

    public List<Issue> getIssuesLike(String description) {
        return issueRepo.findLikeDescription(description);
    }

    public IssueDTO getIssueByIdDTO(Long id) throws ResourceNotFound {
        Optional<Issue> issue = issueRepo.findById(id);

         if (issue.isPresent()) {
            return toDTO(issue.get());
         }

         throw new ResourceNotFound();
         
    }

    public Optional<Issue> getIssueById(Long id) {
        return issueRepo.findById(id);
    }

    public List<Issue> getAllAtStatus(IssueStatus status) {
        return issueRepo.findByStatus(status);
    }

    public void changeIssueStatusTo(Long id, IssueStatus status) {
        Optional<Issue> issueOptional = getIssueById(id);

        if(issueOptional.isPresent()) {
            changeIssueStatusTo(issueOptional.get(), status);
        }
    }

    public void changeIssueStatusTo(Issue issue, IssueStatus status) {

        issue.setStatus(status);
        issueRepo.save(issue);

    }

    public void makeIssueInProgress(Issue issue) {
        changeIssueStatusTo(issue, IssueStatus.IN_PROGRESS);
    }
    
    public void makeIssueInProgress(Long issueId) {
        changeIssueStatusTo(issueId, IssueStatus.IN_PROGRESS);
    }

    public void makeIssueFixed(Issue issue) {
        changeIssueStatusTo(issue, IssueStatus.FIXED);
    }
    
    public void makeIssueFixed(Long issueId) {
        changeIssueStatusTo(issueId, IssueStatus.FIXED);
    }

    public void makeIssueAwaitingTests(Issue issue) {
        changeIssueStatusTo(issue, IssueStatus.AWAITING_TESTS);
    }
    
    public void makeIssueAwaitingTests(Long issueId) {
        changeIssueStatusTo(issueId, IssueStatus.AWAITING_TESTS);
    }

    public void makeIssueQueued(Issue issue) {
        changeIssueStatusTo(issue, IssueStatus.QUEUED);
    }
    
    public void makeIssueQueued(Long issueId) {
        changeIssueStatusTo(issueId, IssueStatus.QUEUED);
    }

    public void deleteIssue(Long issueId) {
        issueRepo.deleteById(issueId);
    }

    public void changeIssueStatusTo(Long id, UpdateIssueStatusRequest body) {
        IssueStatus status = body.getStatus();

        changeIssueStatusTo(id, status);
    }

}
