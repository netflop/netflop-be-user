package com.netflop.be.user.model.response;

import lombok.Data;

@Data
public class SignUpResponse {
    private Boolean userConfirmed;
    private String userSub;
}