package com.netflop.be.user.model.request;

import lombok.Data;

@Data
public class ResendConfirmationCodeRequest {
    private String userName;
}