package com.codapt.issuetracker.features.helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    
    @GetMapping()
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/admin")
    public String helloWorldAdmin() {
        return "Hello world admin";
    }
    
    
}