package com.netflop.be.user.service;
import com.netflop.be.user.model.request.UserLoginRequestPayload;
import com.netflop.be.user.model.response.UserLoginResponsePayload;
import com.netflop.be.user.repository.CognitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CognitoServiceImpl implements CognitoService{
    @Autowired
    private CognitoRepository cognitoRepository;
    @Override
    public UserLoginResponsePayload processLogin(UserLoginRequestPayload userLoginRequestPayload) throws Exception {
        return cognitoRepository.processLogin(userLoginRequestPayload);
    }
}