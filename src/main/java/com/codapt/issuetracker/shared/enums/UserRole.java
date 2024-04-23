package com.codapt.issuetracker.shared.enums;

/** Represents a users role within the issue tracker */
public enum UserRole {

    /** Has the permission to 
     * <ul>
     * <li>perform all CRUD operations on issues</li>
     * <li>add other ADMIN, DEVELOPER & TESTER users</li>
     * </ul>
     */
    ADMIN,

    /** Is the actual developer who will be fixing the issues
     *  and has the permission to 
     * <ul>
     * <li>add an issue</li>
     * <li>change an issues status to IN_PROGRESS or AWAITING TEST</li>
     * </ul>
     */
    DEVELOPER,

    /** Represents a business analyst or QA engineer .etc who has the permission to 
     * <ul>
     * <li>add an issue</li>
     * <li>change to the issue status to any status</li>
     * </ul>
     */
    TESTER
}
