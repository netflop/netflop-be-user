package com.netflop.be.user.service;

import com.netflop.be.user.model.request.ConfirmSignUpRequest;
import com.netflop.be.user.model.request.ResendConfirmationCodeRequest;
import com.netflop.be.user.model.request.SignInRequest;
import com.netflop.be.user.model.request.SignUpRequest;
import com.netflop.be.user.model.response.ConfirmSignUpResponse;
import com.netflop.be.user.model.response.ResendConfirmationCodeResponse;
import com.netflop.be.user.model.response.SignInResponse;
import com.netflop.be.user.model.response.SignUpResponse;

public interface CognitoService {
    SignInResponse processSignIn(SignInRequest signInRequest) throws Exception;
    SignUpResponse processSignUp(SignUpRequest signUpRequest) throws Exception;
    ConfirmSignUpResponse confirmSignUp(ConfirmSignUpRequest confirmSignUpRequest) throws Exception;
    ResendConfirmationCodeResponse resendConfirmationCode(ResendConfirmationCodeRequest request) throws Exception;
}