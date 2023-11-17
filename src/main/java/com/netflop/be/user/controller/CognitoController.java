package com.netflop.be.user.controller;

import com.netflop.be.user.model.request.ConfirmSignUpRequest;
import com.netflop.be.user.model.request.ResendConfirmationCodeRequest;
import com.netflop.be.user.model.request.SignInRequest;
import com.netflop.be.user.model.request.SignUpRequest;
import com.netflop.be.user.model.response.ConfirmSignUpResponse;
import com.netflop.be.user.model.response.ResendConfirmationCodeResponse;
import com.netflop.be.user.model.response.SignInResponse;
import com.netflop.be.user.model.response.SignUpResponse;
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

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest signInRequest){
        try{
            SignInResponse signInResponse = cognitoService.processSignIn(signInRequest);
            return ResponseEntity.ok(signInResponse);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest signUpRequest){
        try{
            SignUpResponse signUpResponse = cognitoService.processSignUp(signUpRequest);
            return ResponseEntity.ok(signUpResponse);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/confirm-sign-up")
    public ResponseEntity<Object> confirmSignUp(@RequestBody ConfirmSignUpRequest confirmSignUpRequest){
        try{
            ConfirmSignUpResponse confirmSignUpResponse = cognitoService.confirmSignUp(confirmSignUpRequest);
            return ResponseEntity.ok(confirmSignUpResponse);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/resend-confirmation-code")
    public ResponseEntity<Object> confirmSignUp(@RequestBody ResendConfirmationCodeRequest resendConfirmationCodeRequest){
        try{
            ResendConfirmationCodeResponse resendConfirmationCodeResponse = cognitoService.resendConfirmationCode(resendConfirmationCodeRequest);
            return ResponseEntity.ok(resendConfirmationCodeResponse);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
