package com.load.user.service.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;


@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor{

    @Autowired
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

//    public FeignClientInterceptor() {
//    }
    
    @Override
    public void apply(RequestTemplate template) {
    
        String token=   oAuth2AuthorizedClientManager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();
 
if(!token.isBlank())
        template.header("Authorization", "Bearer "+token);
else
        System.out.println("Feign Cleint Token : "+ template);
    
    }

 





}
