package com.codapt.issuetracker.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.codapt.issuetracker.features.auth.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthMiddleware implements HandlerInterceptor  {
    

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler) {
        
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);

        if(authorization != null && authorization.startsWith("Bearer ")) {
            String token = extractToken(authorization);
            authService.verifyToken(token);
            

            return true;
        }

        response.setStatus(401);
        return false;
    }

    private String extractToken(String authorization) {
        return authorization.substring(7);
    }

}
