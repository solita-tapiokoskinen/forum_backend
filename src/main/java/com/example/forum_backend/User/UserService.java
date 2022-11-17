package com.example.forum_backend.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public List<User> hello() {
        return List.of(
                new User(1L,"hello","@world","hlowrld")
        );
    }
}
