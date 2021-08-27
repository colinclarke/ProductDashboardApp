package com.colin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.colin.models.Role;
import com.colin.models.User;
import com.colin.repo.RoleRepository;
import com.colin.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api")
public class LandingPageController {

	@Autowired
	private UserDetailsServiceImpl userDetails;

	@Autowired
	private RoleRepository roleRepository;

	@GetMapping("/")
	public String getLandingPage() {
		return "landing-page";
	}

	@GetMapping("/create-new-user")
	public List<Role> createUser() {
		List<Role> r = new ArrayList<>();
		roleRepository.findAll().forEach(r::add);
		return r;

	}

	@PostMapping("/create-new-user")
	public ResponseEntity<HttpStatus> createNewUser(@Valid @RequestBody User u) {
		userDetails.createNewUser(u);

		return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}

}
