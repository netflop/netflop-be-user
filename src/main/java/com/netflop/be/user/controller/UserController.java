package com.netflop.be.user.controller;

import com.netflop.be.user.dto.MessageDto;
import com.netflop.be.user.model.UserResponse;
import com.netflop.be.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

     @GetMapping("/getAdmin")
     @PreAuthorize("hasAuthority('Admin')")
    public UserResponse getAdmin(){
        return userService.getAdmin();
    }

    @GetMapping("/getUser")
    @PreAuthorize("hasAuthority('User')")
    public UserResponse getUser(){
        return userService.getUser();
    }

    @GetMapping("/hello")
    public ResponseEntity<MessageDto> hello(){
        return ResponseEntity.ok(new MessageDto("Welcome to NetFlop"));
    }

}
