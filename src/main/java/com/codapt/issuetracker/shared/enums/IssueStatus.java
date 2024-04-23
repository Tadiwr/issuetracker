package com.codapt.issuetracker.shared.enums;

/** Represents the state of an issue */
public enum IssueStatus {

    /* A test has been added to the issue tracker */
    QUEUED, 

    /** The issue is awaiting to be tested again to see if it was fixed */
    AWAITING_TESTS,

    /** The was fixed and closed */
    FIXED,

    /** The issue is currently being fixed */
    IN_PROGRESS
}
