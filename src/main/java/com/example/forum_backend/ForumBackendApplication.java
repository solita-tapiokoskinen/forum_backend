package com.example.forum_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ForumBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumBackendApplication.class, args);
    }

}
