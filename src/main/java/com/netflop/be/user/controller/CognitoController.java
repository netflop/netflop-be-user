package com.netflop.be.user.controller;

import com.netflop.be.user.model.request.UserLoginRequestPayload;
import com.netflop.be.user.model.response.UserLoginResponsePayload;
import com.netflop.be.user.service.CognitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cognito")
public class CognitoController {
    @Autowired
    private CognitoService cognitoService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequestPayload userLoginRequestPayload){
        try{
            UserLoginResponsePayload userLoginResponsePayload = cognitoService.processLogin(userLoginRequestPayload);
            return ResponseEntity.ok(userLoginResponsePayload);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
