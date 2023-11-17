package com.netflop.be.user.model.request;
import lombok.Data;

@Data
public class UserLoginRequestPayload {
    private String userName;
    private String password;
    private String newPassword;
}