package com.netflop.be.user.service;

import com.netflop.be.user.model.UserResponse;
import com.netflop.be.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse findAllUser() {

        return userRepository.findAllUser();
    }

    @Override
    public UserResponse getAdmin() {
        return userRepository.getAdmin();
    }

    @Override
    public UserResponse getUser() {
        return userRepository.getUser();
    }
}
