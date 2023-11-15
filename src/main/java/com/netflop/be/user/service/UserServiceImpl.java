package com.netflop.be.user.service;

import com.netflop.be.user.model.User;
import com.netflop.be.user.model.response.UserResponse;
import com.netflop.be.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserResponse findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public UserResponse save(User user) {
        return userRepository.save(user);
    }

    @Override
    public String deleteByUserId(String userId) {
        return userRepository.deleteByUserId(userId);
    }

    @Override
    public String updateUser(String userId, User user) {
        return userRepository.updateUser(userId,user);
    }
}