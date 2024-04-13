package com.load.user.service.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.load.user.service.config.interceptor.RestTemplateInterceptor;


@Configuration
public class MyConfig {

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;

	@Autowired
	private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

    private Logger logger =  LoggerFactory.getLogger(MyConfig.class);


    	@Bean
	 @LoadBalanced
	 public RestTemplate restTemplate(){
	RestTemplate restTemplate = new RestTemplate();
	List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = restTemplate.getInterceptors();
	
	System.out.println(clientHttpRequestInterceptors);
	
	if (CollectionUtils.isEmpty(clientHttpRequestInterceptors)) {
		clientHttpRequestInterceptors = new ArrayList<>();
    }
	System.out.println("Before My config Client Http Request "+clientHttpRequestInterceptors);
	clientHttpRequestInterceptors.add(new RestTemplateInterceptor(manager(clientRegistrationRepository, oAuth2AuthorizedClientRepository ), restTemplate));
	System.out.println("After My config Client Http Request "+clientHttpRequestInterceptors.get(0).toString());
	restTemplate.setInterceptors(clientHttpRequestInterceptors);
	
	System.out.println(restTemplate);
	
	return restTemplate;
	}

	@Bean
	public OAuth2AuthorizedClientManager manager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository){
	
	OAuth2AuthorizedClientProvider provider= OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager=	new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientRepository);
		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);

		return defaultOAuth2AuthorizedClientManager;
	}

}
