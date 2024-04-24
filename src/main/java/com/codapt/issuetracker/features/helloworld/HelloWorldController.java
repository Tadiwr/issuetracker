package com.codapt.issuetracker.features.helloworld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codapt.issuetracker.features.users.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
    
    @GetMapping()
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("admin")
    public String helloWorldAdmin(@RequestAttribute User user) {
        return "Hello world admin, " + user.getName();
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
