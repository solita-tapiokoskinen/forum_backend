package com.example.forum_backend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository;}

    @Autowired
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void registerUser(User user) {
        boolean exists = userRepository.existsById(user.getId());
        if (exists){
            System.out.println("nono");
        }
        else {
            userRepository.save(user);
            }
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
