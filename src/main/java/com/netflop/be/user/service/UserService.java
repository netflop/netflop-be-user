package com.netflop.be.user.service;

import com.netflop.be.user.model.UserResponse;

public interface UserService {
    UserResponse findAllUser();
    UserResponse getAdmin();
    UserResponse getUser();
}
