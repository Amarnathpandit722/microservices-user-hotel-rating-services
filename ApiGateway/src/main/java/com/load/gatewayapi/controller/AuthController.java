package com.load.gatewayapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.load.gatewayapi.models.AuthReponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
// OKTA -Sign-in redirect URIs----           http://localhost:8084/login/oauth2/code/okta

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity <AuthReponse>  login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
           @AuthenticationPrincipal OidcUser user, Model model
    ){
        logger.info("useremail id {}",user.getEmail());

        AuthReponse authReponse = new AuthReponse();

        authReponse.setUserId(user.getEmail());

        authReponse.setAccessToken(client.getAccessToken().getTokenValue());

        authReponse.setRefreshToken(client.getRefreshToken().getTokenValue());
        authReponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());

    List<String>authorities=    user.getAuthorities().stream().map(grantedAuth->{
                return grantedAuth.getAuthority();
        }).collect(Collectors.toList()); 
authReponse.setAuthrities(authorities);

        return new ResponseEntity<AuthReponse>(authReponse, HttpStatus.OK);
    }



}
