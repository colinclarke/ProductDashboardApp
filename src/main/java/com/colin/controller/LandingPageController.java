package com.colin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colin.models.DummyUser;
import com.colin.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api")
public class LandingPageController {

	@Autowired
	private UserDetailsServiceImpl userDetails;

	@PostMapping("/create-new-user")
	public ResponseEntity<HttpStatus> createNewUser(@Valid @RequestBody DummyUser user) {
		if (userDetails.createNewUser(user)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.CONFLICT);
	}

}
