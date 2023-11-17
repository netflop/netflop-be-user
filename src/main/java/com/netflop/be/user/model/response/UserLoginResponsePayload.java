package com.netflop.be.user.model.response;
import lombok.Data;

@Data
public class UserLoginResponsePayload {
    private String idToken;
    private String accessToken;
    private String refreshToken;
}