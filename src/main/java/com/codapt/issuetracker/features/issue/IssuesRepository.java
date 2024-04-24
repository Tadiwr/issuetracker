package com.codapt.issuetracker.features.issue;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.codapt.issuetracker.features.users.User;
import com.codapt.issuetracker.shared.enums.IssueStatus;



@Repository
public interface IssuesRepository extends CrudRepository<Issue, Long> {

    /** Get issues created by a certain user */
    List<Issue> findByCreatedBy(User createdBy);

    /** Get issues at a certain status */
    List<Issue> findByStatus(IssueStatus status);

    @Query("SELECT e FROM Issue e WHERE e.description LIKE %:description%")
    List<Issue> findLikeDescription(String description);
}
