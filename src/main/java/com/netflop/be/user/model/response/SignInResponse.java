package com.netflop.be.user.model.response;
import lombok.Data;

@Data
public class SignInResponse {
    private String idToken;
    private String accessToken;
    private String refreshToken;
}