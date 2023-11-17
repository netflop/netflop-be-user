package com.netflop.be.user.repository;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.netflop.be.user.model.request.UserLoginRequestPayload;
import com.netflop.be.user.model.response.UserLoginResponsePayload;
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

    public UserLoginResponsePayload processLogin(UserLoginRequestPayload userLoginRequestPayload) throws Exception {
        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.standard().withRegion(region).build();
        UserLoginResponsePayload userLoginResponsePayload = new UserLoginResponsePayload();
        final Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", userLoginRequestPayload.getUserName());
        authParams.put("PASSWORD", userLoginRequestPayload.getPassword());
        final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
        authRequest.withAuthFlow(AuthFlowType.ADMIN_USER_PASSWORD_AUTH).withClientId(clientId)
                .withUserPoolId(userPoolId).withAuthParameters(authParams);
        try {
            AdminInitiateAuthResult result = cognitoClient.adminInitiateAuth(authRequest);
            AuthenticationResultType authenticationResult = null;
            if (result.getChallengeName() != null && !result.getChallengeName().isEmpty()) {
                System.out.println("Challenge Name is " + result.getChallengeName());
                if (result.getChallengeName().contentEquals("NEW_PASSWORD_REQUIRED")) {
                    if (userLoginRequestPayload.getPassword() == null) {
                        throw new Exception("User must change password " + result.getChallengeName());
                    } else {
                        final Map<String, String> challengeResponses = new HashMap<>();
                        challengeResponses.put("USERNAME", userLoginRequestPayload.getUserName());
                        challengeResponses.put("PASSWORD", userLoginRequestPayload.getPassword());
                        // add new password
                        challengeResponses.put("NEW_PASSWORD", userLoginRequestPayload.getNewPassword());
                        final AdminRespondToAuthChallengeRequest request = new AdminRespondToAuthChallengeRequest()
                                .withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                                .withChallengeResponses(challengeResponses).withClientId(clientId)
                                .withUserPoolId(userPoolId).withSession(result.getSession());
                        AdminRespondToAuthChallengeResult resultChallenge = cognitoClient.adminRespondToAuthChallenge(request);
                        authenticationResult = resultChallenge.getAuthenticationResult();
                        userLoginResponsePayload.setAccessToken(authenticationResult.getAccessToken());
                        userLoginResponsePayload.setRefreshToken(authenticationResult.getRefreshToken());
                    }
                } else {
                    throw new Exception("User has other challenge " + result.getChallengeName());
                }
                cognitoClient.shutdown();
                return userLoginResponsePayload;
            } else {
                System.out.println("User has no challenge");
                authenticationResult = result.getAuthenticationResult();
                userLoginResponsePayload.setIdToken(authenticationResult.getIdToken());
                userLoginResponsePayload.setAccessToken(authenticationResult.getAccessToken());
                userLoginResponsePayload.setRefreshToken(authenticationResult.getRefreshToken());
                cognitoClient.shutdown();
                return userLoginResponsePayload;
            }
        } catch (InvalidParameterException e) {
            cognitoClient.shutdown();
            throw new Exception(e.getErrorMessage());
        } catch (Exception e) {
            cognitoClient.shutdown();
            throw new Exception(e.getMessage());
        }
    }
}