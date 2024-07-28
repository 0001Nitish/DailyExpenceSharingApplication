package com.example.expensesharing.service;

import com.example.expensesharing.dto.UserDTO;
import com.example.expensesharing.model.User;
import com.example.expensesharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(userDTO.getEmail(), userDTO.getName(), userDTO.getMobile());
        userRepository.save(user);
        // create a new UserDTO object and set the email, name, and mobile
        return new UserDTO(user.getEmail(), user.getName(), user.getMobile());
    }

    public User getUser(String email) {
        return userRepository.findById(email).orElse(null);
    }
}