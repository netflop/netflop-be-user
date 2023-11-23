package com.netflop.be.user.model.request;
import lombok.Data;

@Data
public class ForgotPasswordRequest {
    private String userName;
}