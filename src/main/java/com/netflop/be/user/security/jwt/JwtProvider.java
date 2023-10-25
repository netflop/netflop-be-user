package com.netflop.be.user.security.jwt;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProvider {
    private static final String USERNAME_FIELD = "cognito:username";
    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ROLE_FIELD = "cognito:groups";

    @Value("${com.tutorial.jwt.aws.identityPoolUrl}")
    public String identityPoolUrl;

    @Autowired
    ConfigurableJWTProcessor configurableJWTProcessor;

    public Authentication authenticate(HttpServletRequest request) throws Exception {
        String token = getToken(request.getHeader(AUTHORIZATION_HEADER));
        if(token != null && !token.isEmpty()){
            JWTClaimsSet claims = configurableJWTProcessor.process(getToken(token), null);
            validateToken(claims);
            String username = getUsername(claims);
            if(username != null){
                // TO DO set role
                String roles = getRoles(claims);
                //get role in token
                List<GrantedAuthority> authorities = rolesToList(roles).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                log.info(authorities.toString());
                //get data in DB, but now we set it manually
                User user = new User(username, "", authorities);
                return new JwtAuthenticator(authorities, user, claims);
            }
        }
        return null;
    }
    private String getUsername(JWTClaimsSet claims) {
        return claims.getClaim(USERNAME_FIELD).toString();
    }

    private void validateToken(JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(identityPoolUrl))
            throw new Exception("JWT not valid");
    }

    private String getToken(String token){
        return token.startsWith(BEARER) ? token.substring(BEARER.length()) : token;
    }

    private String getRoles(JWTClaimsSet claims){
        return claims.getClaim(ROLE_FIELD).toString();
    }

    private List<String> rolesToList(String roles){
        String noSquare = roles.replace("[", "").replace("]", "");
        String noQuotes = noSquare.replace("\"", "");
        String noSpaces = noQuotes.replace(" ", "");
        return List.of(noSpaces.split(","));
    }
}
