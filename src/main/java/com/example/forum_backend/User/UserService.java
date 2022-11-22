package com.example.forum_backend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository;}

    @Autowired
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void registerUser(User user) {
        System.out.println("Hello"+user.toString());
        userRepository.save(user);
    }

}
