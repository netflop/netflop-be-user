package com.netflop.be.user.service;
import com.netflop.be.user.model.User;
import com.netflop.be.user.model.response.UserResponse;

public interface UserService {
    UserResponse findByUserId(String userId);
    UserResponse save(User user);
    String deleteByUserId(String userId);
    String updateUser(String userId,User user);
}