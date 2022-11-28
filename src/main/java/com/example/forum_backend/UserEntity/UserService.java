package com.example.forum_backend.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository;}

    @Autowired
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public void registerUser(UserEntity userEntity) {
        boolean exists = userRepository.existsById(userEntity.getId());
        if (exists){
            System.out.println("nono");
        }
        else {
            userRepository.save(userEntity);
            }
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }
}
