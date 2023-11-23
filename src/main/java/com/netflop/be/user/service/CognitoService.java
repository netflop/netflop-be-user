package com.netflop.be.user.service;

import com.netflop.be.user.model.request.*;
import com.netflop.be.user.model.response.*;

public interface CognitoService {
    SignInResponse processSignIn(SignInRequest signInRequest) throws Exception;
    SignUpResponse processSignUp(SignUpRequest signUpRequest) throws Exception;
    ConfirmSignUpResponse confirmSignUp(ConfirmSignUpRequest confirmSignUpRequest) throws Exception;
    ResendConfirmationCodeResponse resendConfirmationCode(ResendConfirmationCodeRequest request) throws Exception;
    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) throws Exception;
    ConfirmForgotPasswordResponse confirmForgotPassword(com.netflop.be.user.model.request.ConfirmForgotPasswordRequest request) throws Exception;
}