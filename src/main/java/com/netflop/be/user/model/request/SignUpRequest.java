package com.netflop.be.user.model.request;

import com.amazonaws.services.cognitoidp.model.AttributeType;
import lombok.Data;

@Data
public class SignUpRequest {
    private String userName;
    private String password;
    private java.util.List<AttributeType> userAttributes;
}