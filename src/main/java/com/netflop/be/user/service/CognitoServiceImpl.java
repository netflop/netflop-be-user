package com.netflop.be.user.service;

import com.netflop.be.user.model.request.*;
import com.netflop.be.user.model.response.*;
import com.netflop.be.user.repository.CognitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CognitoServiceImpl implements CognitoService{
    @Autowired
    private CognitoRepository cognitoRepository;
    @Override
    public SignInResponse processSignIn(SignInRequest signInRequest) throws Exception {
        return cognitoRepository.processSignIn(signInRequest);
    }

    @Override
    public SignUpResponse processSignUp(SignUpRequest signUpRequest) throws Exception {
        return cognitoRepository.processSignUp(signUpRequest);
    }

    @Override
    public ConfirmSignUpResponse confirmSignUp(ConfirmSignUpRequest confirmSignUpRequest) throws Exception {
        return cognitoRepository.confirmSignUp(confirmSignUpRequest);
    }

    @Override
    public ResendConfirmationCodeResponse resendConfirmationCode(ResendConfirmationCodeRequest resendConfirmationCodeRequest) throws Exception {
        return cognitoRepository.resendConfirmationCode(resendConfirmationCodeRequest);
    }

    @Override
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws Exception {
        return cognitoRepository.forgotPassword(forgotPasswordRequest);
    }

    @Override
    public ConfirmForgotPasswordResponse confirmForgotPassword(ConfirmForgotPasswordRequest confirmForgotPasswordRequest) throws Exception {
        return cognitoRepository.confirmForgotPassword(confirmForgotPasswordRequest);
    }
}