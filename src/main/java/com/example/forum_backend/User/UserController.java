package com.example.forum_backend.User;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api")
@CrossOrigin(origins = { "http://localhost:3000" })
public class UserController {

    @GetMapping("/hello")
    public List<User> hello() {
        return List.of(
                new User(1L,"hello","@world","hlowrld")
        );
    }

}
