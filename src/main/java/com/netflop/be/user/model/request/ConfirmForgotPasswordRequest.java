package com.netflop.be.user.model.request;
import lombok.Data;
@Data
public class ConfirmForgotPasswordRequest {
    private String userName;
    private String confirmationCode;
    private String password;
}