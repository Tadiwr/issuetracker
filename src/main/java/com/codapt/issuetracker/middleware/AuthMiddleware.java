package com.codapt.issuetracker.middleware;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.codapt.issuetracker.features.auth.AuthService;
import com.codapt.issuetracker.features.auth.providers.authorization.PermissionsMapper;
import com.codapt.issuetracker.features.users.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthMiddleware implements HandlerInterceptor  {
    

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler) {
        
        String authorization = request.getHeader("Authorization");

        if(authorization != null && authorization.startsWith("Bearer ")) {
            String token = extractToken(authorization);
            
            Optional<User> user = authService.getTokenBearer(token);

            if(authService.verifyToken(token) && user.isPresent()) {

                request.setAttribute("user", user.get());

                boolean hasPermission = checkPermissions(request.getRequestURI(), user.get());
                if (hasPermission) {
                    return true;
                }

                response.setStatus(406);
                return false;
            }

        }

        response.setStatus(401);
        return false;
    }

    /** Takes in a user object and a path and checks if the user has permission to access the api at that path */
    private boolean checkPermissions(String path, User user) {
        new PermissionsMapper();
        return true;
    }

    private String extractToken(String authorization) {
        
        return authorization.substring(7);
    }

}
