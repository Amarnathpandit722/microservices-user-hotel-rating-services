package com.load.user.service.config.interceptor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.client.RestTemplate;

import feign.RequestTemplate;


public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

	@Autowired
	private RequestTemplate template;
	private RestTemplate restTemplate;

	private final String AUTH= "Authorization";
	private Logger logger = LoggerFactory.getLogger(RestTemplateInterceptor.class);
	private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

	public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager, RestTemplate restTemplate) {
		this.restTemplate=restTemplate;
		this.oAuth2AuthorizedClientManager = manager;

	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		String token = oAuth2AuthorizedClientManager.authorize(
				OAuth2AuthorizeRequest
				.withClientRegistrationId("my-internal-client")
				.principal("internal").build())
				.getAccessToken().getTokenValue();

		
		System.out.println("******* Rest Template Inteceptor : " + token);

		
		//template.header(AUTH, "Bearer " + token);
	

		System.out.println("Template Header : " + template);

		request.getHeaders().add(AUTH, "Bearer " + token);

		System.out.println("Request Header : " + request);

		return execution.execute(request, body);

	}

}
