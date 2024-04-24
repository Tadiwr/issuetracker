package com.codapt.issuetracker.features.auth.providers.authorization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codapt.issuetracker.shared.enums.UserRole;

public class PermissionsMapper {
    
    private Map<UserRole, List<String>> map;

    public PermissionsMapper() {
        this.map = initializePathMappings();
    }

    private Map<UserRole, List<String>> initializePathMappings() {
        return new PermissionsMapBuilder()
            .setMappings(UserRole.ADMIN, List.of("/api/admin/**"))
            .setMappings(UserRole.TESTER, List.of("/api/tester/**"))
            .setMappings(UserRole.DEVELOPER, List.of("/api/dev/**"))
            .build();
    }
    


}

class PermissionsMapBuilder {

    private Map<UserRole, List<String>> map = new HashMap<>();

    public PermissionsMapBuilder() {
        map.put(UserRole.ADMIN, new ArrayList<String>());
        map.put(UserRole.DEVELOPER, new ArrayList<String>());
        map.put(UserRole.TESTER, new ArrayList<String>());
    }

    /** Appends a path pattern to a list of already existing path patterns  */
    public PermissionsMapBuilder append(UserRole role, String pathPattern) {

        List<String> pathList =  map.get(role);
        pathList.add(pathPattern);
        map.replace(role, pathList);

        return this;
    }

    /** Overwrites and sets a list of path patterns to role */
    public PermissionsMapBuilder setMappings(UserRole role, List<String> pathPatterns) {

        map.replace(role, pathPatterns);

        return this;
    }

    public Map<UserRole, List<String>> build() {
        // Append all paths from the other routes to Admin
        List<String> adminPaths = map.get(UserRole.ADMIN);
        List<String> testerPaths = map.get(UserRole.TESTER);
        List<String> devPaths = map.get(UserRole.DEVELOPER);

        
        ArrayList<String> combinedTesterPaths = new ArrayList<>(testerPaths);
        combinedTesterPaths.addAll(devPaths);

        ArrayList<String> combinedAdminPaths = new ArrayList<>(adminPaths);
        combinedAdminPaths.addAll(combinedTesterPaths);

        map.replace(UserRole.ADMIN, combinedAdminPaths);
        map.replace(UserRole.TESTER, combinedTesterPaths);

        printAllPaths(UserRole.ADMIN);
        printAllPaths(UserRole.TESTER);
        printAllPaths(UserRole.DEVELOPER);

        return map;
    }

    private void printAllPaths(UserRole role) {
        System.err.println("\n\n "+ role + " ->");

        map.get(role).forEach(path -> {
            System.out.print( " " + path);
        });
    }

    

}
