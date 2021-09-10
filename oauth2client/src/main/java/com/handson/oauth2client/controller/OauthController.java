package com.handson.oauth2client.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OauthController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage() {
		return "home";
	}
	
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	@GetMapping("/userdetails")
	public ModelAndView getUserDetails(OAuth2AuthenticationToken authentication) {
		OAuth2AuthorizedClient client = authorizedClientService
				.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userdetails");
		modelAndView.addObject("client", client);
		
		String userInfoEndpointUri = client.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
		HttpEntity entity = new HttpEntity("", headers);
		
		ResponseEntity<Map> response  = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
		
		modelAndView.addObject("userAttributes", response.getBody());
		
		return modelAndView;
	}

	@Value("${resource-url}")
	private String resourceEndpointUri;

	@GetMapping("/resourcedetails")
	public ModelAndView getResourceDetails(OAuth2AuthenticationToken authentication) {
		OAuth2AuthorizedClient client = authorizedClientService
				.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("resourcedetails");
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
		HttpEntity entity = new HttpEntity("", headers);
		
		//modify this as per response
		//github public repo is array of reposdetails so mapping that to list of map
		ResponseEntity<List<Map<String, Object>>> response  = restTemplate.exchange(resourceEndpointUri, 
				HttpMethod.GET, entity, 
				new ParameterizedTypeReference<List<Map<String, Object>>>() {});
		
		modelAndView.addObject("resourcedetails", response.getBody());
		
		return modelAndView;
	}


}
