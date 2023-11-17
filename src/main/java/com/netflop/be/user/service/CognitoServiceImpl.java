package com.netflop.be.user.service;

import com.netflop.be.user.model.request.ConfirmSignUpRequest;
import com.netflop.be.user.model.request.ResendConfirmationCodeRequest;
import com.netflop.be.user.model.request.SignInRequest;
import com.netflop.be.user.model.request.SignUpRequest;
import com.netflop.be.user.model.response.ConfirmSignUpResponse;
import com.netflop.be.user.model.response.ResendConfirmationCodeResponse;
import com.netflop.be.user.model.response.SignInResponse;
import com.netflop.be.user.model.response.SignUpResponse;
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
}