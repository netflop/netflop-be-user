package com.netflop.be.user.service;
import com.netflop.be.user.model.request.UserLoginRequestPayload;
import com.netflop.be.user.model.response.UserLoginResponsePayload;

public interface CognitoService {
    UserLoginResponsePayload processLogin(UserLoginRequestPayload userLoginRequestPayload) throws Exception;
}