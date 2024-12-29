package com.example.jdbc.userAttendance.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }
    public Optional<User> findByUserName(String name){
        return userRepository.findByUserName(name);
    }
}
