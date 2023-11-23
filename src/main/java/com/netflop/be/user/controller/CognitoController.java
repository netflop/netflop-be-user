package com.netflop.be.user.controller;

import com.netflop.be.user.model.request.*;
import com.netflop.be.user.model.response.*;
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

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        try{
            ForgotPasswordResponse forgotPasswordResponse = cognitoService.forgotPassword(forgotPasswordRequest);
            return ResponseEntity.ok(forgotPasswordResponse);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/confirm-forgot-password")
    public ResponseEntity<Object> confirmForgotPassword(@RequestBody ConfirmForgotPasswordRequest confirmForgotPasswordRequest){
        try{
            ConfirmForgotPasswordResponse confirmForgotPasswordResponse = cognitoService.confirmForgotPassword(confirmForgotPasswordRequest);
            return ResponseEntity.ok(confirmForgotPasswordResponse);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
