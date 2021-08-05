package com.colin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.colin.service.UserDetailsServiceImpl;


@Controller
public class LandingPageController {

	@Autowired
	private UserDetailsServiceImpl userDetails;
	
	@GetMapping("")
	public String getLandingPage() {
		return "landing-page";
	}
	
	@GetMapping("/create-new-user")
	public String createUser() {
		return "create-new-user";
	}
	
	@PostMapping("/create-new-user")
	public String createNewUser(@RequestParam String name, @RequestParam String password) {
		
		userDetails.createNewUser(name, password);
		
		return "redirect:/products";
	}

}
