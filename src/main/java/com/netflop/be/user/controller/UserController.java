package com.netflop.be.user.controller;

import com.netflop.be.user.model.response.MessageResponse;
import com.netflop.be.user.model.User;
import com.netflop.be.user.model.response.UserResponse;
import com.netflop.be.user.repository.UserRepository;
import com.netflop.be.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MessageResponse> hello(){
        return ResponseEntity.ok(new MessageResponse("Welcome to NetFlop"));
    }

    @GetMapping("/{userId}")
    //@PreAuthorize("hasAuthority('Admin')")
    public UserResponse getByUserId(@PathVariable("userId") String userId){
        return userService.findByUserId(userId);
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('Admin')")
    public UserResponse addUser(@RequestBody User user){
        return userService.save(user);
    }
    @PutMapping("/{userId}")
    //@PreAuthorize("hasAuthority('Admin')")
    public String updateUser(@PathVariable("userId") String userId, @RequestBody User user){
        return userService.updateUser(userId,user);
    }

    @DeleteMapping("/{userId}")
    //@PreAuthorize("hasAuthority('Admin')")
    public String updateUser(@PathVariable("userId") String userId){
        return userService.deleteByUserId(userId);
    }
}