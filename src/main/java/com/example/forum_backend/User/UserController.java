package com.example.forum_backend.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }

}
