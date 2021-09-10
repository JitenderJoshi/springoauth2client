package com.handson.oauth2client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OauthController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage() {
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}

}