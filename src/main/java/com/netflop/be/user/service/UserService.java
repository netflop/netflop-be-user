package com.netflop.be.user.service;

import com.netflop.be.user.model.User;

public interface UserService {
    User findByUserId(String userId);
    User save(User user);
    String deleteByUserId(String userId);
    String updateUser(String userId,User user);
}