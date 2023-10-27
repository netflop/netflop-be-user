package com.netflop.be.user.controller;

import com.netflop.be.user.dto.MessageDto;
import com.netflop.be.user.model.User;
import com.netflop.be.user.repository.UserRepository;
import com.netflop.be.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<MessageDto> hello(){
        return ResponseEntity.ok(new MessageDto("Welcome to NetFlop"));
    }

    @GetMapping("/get/{userId}")
    @PreAuthorize("hasAuthority('Admin')")
    public User getByUserId(@PathVariable("userId") String userId){
        return userService.findByUserId(userId);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('Admin')")
    public User addUser(@RequestBody User user){
        return userService.save(user);
    }
    @PutMapping("/update/{userId}")
    @PreAuthorize("hasAuthority('Admin')")
    public String updateUser(@PathVariable("userId") String userId, @RequestBody User user){
        return userService.updateUser(userId,user);
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('Admin')")
    public String updateUser(@PathVariable("userId") String userId){
        return userService.deleteByUserId(userId);
    }
}