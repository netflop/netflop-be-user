package com.netflop.be.user.repository;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.netflop.be.user.model.request.ConfirmSignUpRequest;
import com.netflop.be.user.model.request.SignInRequest;
import com.netflop.be.user.model.request.SignUpRequest;
import com.netflop.be.user.model.response.ConfirmSignUpResponse;
import com.netflop.be.user.model.response.ResendConfirmationCodeResponse;
import com.netflop.be.user.model.response.SignInResponse;
import com.netflop.be.user.model.response.SignUpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CognitoRepository {
    @Value(value = "${aws.cognito.userPoolId}")
    private String userPoolId;
    @Value(value = "${aws.cognito.clientId}")
    private String clientId;
    @Value(value = "${aws.cognito.region}")
    private String region;

    public SignInResponse processSignIn(SignInRequest signInRequest) throws Exception {
        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard().withRegion(region).build();
        SignInResponse signInResponse = new SignInResponse();
        final Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", signInRequest.getUserName());
        authParams.put("PASSWORD", signInRequest.getPassword());
        final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
        authRequest.withAuthFlow(AuthFlowType.ADMIN_USER_PASSWORD_AUTH).withClientId(clientId)
                .withUserPoolId(userPoolId).withAuthParameters(authParams);
        try {
            AdminInitiateAuthResult result = cognitoClient.adminInitiateAuth(authRequest);
            AuthenticationResultType authenticationResult = null;
            if (result.getChallengeName() != null && !result.getChallengeName().isEmpty()) {
                System.out.println("Challenge Name is " + result.getChallengeName());
                if (result.getChallengeName().contentEquals("NEW_PASSWORD_REQUIRED")) {
                    if (signInRequest.getPassword() == null) {
                        throw new Exception("User must change password " + result.getChallengeName());
                    } else {
                        final Map<String, String> challengeResponses = new HashMap<>();
                        challengeResponses.put("USERNAME", signInRequest.getUserName());
                        challengeResponses.put("PASSWORD", signInRequest.getPassword());
                        // add new password
                        challengeResponses.put("NEW_PASSWORD", signInRequest.getNewPassword());
                        final AdminRespondToAuthChallengeRequest request = new AdminRespondToAuthChallengeRequest()
                                .withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                                .withChallengeResponses(challengeResponses).withClientId(clientId)
                                .withUserPoolId(userPoolId).withSession(result.getSession());
                        AdminRespondToAuthChallengeResult resultChallenge = cognitoClient.adminRespondToAuthChallenge(request);
                        authenticationResult = resultChallenge.getAuthenticationResult();
                        signInResponse.setAccessToken(authenticationResult.getAccessToken());
                        signInResponse.setRefreshToken(authenticationResult.getRefreshToken());
                    }
                } else {
                    throw new Exception("User has other challenge " + result.getChallengeName());
                }
                cognitoClient.shutdown();
                return signInResponse;
            } else {
                System.out.println("User has no challenge");
                authenticationResult = result.getAuthenticationResult();
                signInResponse.setIdToken(authenticationResult.getIdToken());
                signInResponse.setAccessToken(authenticationResult.getAccessToken());
                signInResponse.setRefreshToken(authenticationResult.getRefreshToken());
                cognitoClient.shutdown();
                return signInResponse;
            }
        } catch (InvalidParameterException e) {
            cognitoClient.shutdown();
            throw new Exception(e.getErrorMessage());
        } catch (Exception e) {
            cognitoClient.shutdown();
            throw new Exception(e.getMessage());
        }
    }

    public SignUpResponse processSignUp(SignUpRequest request) throws Exception {
        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard().withRegion(region).build();
        SignUpResponse userRegisterResponse = new SignUpResponse();
        final com.amazonaws.services.cognitoidp.model.SignUpRequest signUpRequest = new com.amazonaws.services.cognitoidp.model.SignUpRequest();
        signUpRequest.withClientId(clientId)
                .withUsername(request.getUserName())
                .withPassword(request.getPassword())
                .withUserAttributes(request.getUserAttributes());
        try {
            SignUpResult result = cognitoClient.signUp(signUpRequest);
            userRegisterResponse.setUserConfirmed(result.getUserConfirmed());
            userRegisterResponse.setUserSub(result.getUserSub());
            cognitoClient.shutdown();
            return userRegisterResponse;
        } catch (Exception e) {
            cognitoClient.shutdown();
            throw new Exception(e.getMessage());
        }
    }

    public ConfirmSignUpResponse confirmSignUp(ConfirmSignUpRequest request) throws Exception {
        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard().withRegion(region).build();
        ConfirmSignUpResponse userConfirmSignUpResponse = new ConfirmSignUpResponse();
        final com.amazonaws.services.cognitoidp.model.ConfirmSignUpRequest confirmSignUpRequest = new com.amazonaws.services.cognitoidp.model.ConfirmSignUpRequest();
        confirmSignUpRequest.withClientId(clientId)
                .withUsername(request.getUserName())
                .withConfirmationCode(request.getConfirmationCode());
        try {
            ConfirmSignUpResult result = cognitoClient.confirmSignUp(confirmSignUpRequest);
            if(result.getSdkHttpMetadata().getHttpStatusCode() == 200){
                userConfirmSignUpResponse.setMessage("Confirm sign up success");
            }
            cognitoClient.shutdown();
            return userConfirmSignUpResponse;
        } catch (Exception e) {
            cognitoClient.shutdown();
            throw new Exception(e.getMessage());
        }
    }

    public ResendConfirmationCodeResponse resendConfirmationCode(com.netflop.be.user.model.request.ResendConfirmationCodeRequest request) throws Exception {
        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard().withRegion(region).build();
        ResendConfirmationCodeResponse resendConfirmationCodeResponse = new ResendConfirmationCodeResponse();
        final com.amazonaws.services.cognitoidp.model.ResendConfirmationCodeRequest resendConfirmationCodeRequest = new com.amazonaws.services.cognitoidp.model.ResendConfirmationCodeRequest();
        resendConfirmationCodeRequest.withClientId(clientId)
                .withUsername(request.getUserName());
        try {
            ResendConfirmationCodeResult result = cognitoClient.resendConfirmationCode(resendConfirmationCodeRequest);
            if(result.getSdkHttpMetadata().getHttpStatusCode() == 200){
                resendConfirmationCodeResponse.setCodeDeliveryDetails(result.getCodeDeliveryDetails());
            }
            cognitoClient.shutdown();
            return resendConfirmationCodeResponse;
        } catch (Exception e) {
            cognitoClient.shutdown();
            throw new Exception(e.getMessage());
        }
    }
}