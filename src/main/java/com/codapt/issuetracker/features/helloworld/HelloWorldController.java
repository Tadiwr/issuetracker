package com.codapt.issuetracker.features.helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
    
    @GetMapping()
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("admin")
    public String helloWorldAdmin() {
        return "Hello world admin";
    }
    
    @GetMapping("tester")
    public String helloWorldTester() {
        return "Hello world tester";
    }
    
    @GetMapping("dev")
    public String helloWorldDev() {
        return "Hello world dev";
    }
}
