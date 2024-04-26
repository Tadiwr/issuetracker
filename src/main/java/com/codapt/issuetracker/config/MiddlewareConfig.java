package com.codapt.issuetracker.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codapt.issuetracker.middleware.AuthMiddleware;

@Configuration
public class MiddlewareConfig  implements WebMvcConfigurer {
    
    @Autowired
    private AuthMiddleware authMiddleware;

    private  List<String> authMiddlewarePatterns = List.of("/auth/logout", "/api/**", "/auth/test");

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(authMiddleware).addPathPatterns(authMiddlewarePatterns);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
