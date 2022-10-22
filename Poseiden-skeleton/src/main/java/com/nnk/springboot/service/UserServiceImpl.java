package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        User thisUser = userRepository.getUserByUsername(user.getUsername());
        if (thisUser == null) {
            userRepository.save(user);
        }
    }
}
