package com.netflop.be.user.model.request;

import lombok.Data;

@Data
public class ConfirmSignUpRequest {
    private String userName;
    private String confirmationCode;
}