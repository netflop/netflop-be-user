package com.netflop.be.user.model.response;
import com.amazonaws.services.cognitoidp.model.CodeDeliveryDetailsType;
import lombok.Data;

@Data
public class ForgotPasswordResponse {
    private CodeDeliveryDetailsType codeDeliveryDetails;
}