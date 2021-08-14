package com.colin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.colin.models.Role;
import com.colin.repo.RoleRepository;
import com.colin.service.UserDetailsServiceImpl;

@Controller
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
	public ModelAndView createUser(ModelAndView mav) {
		List<Role> r = new ArrayList<>();
		roleRepository.findAll().forEach(r::add);
		mav.addObject("roles", r);
		mav.setViewName("create-new-user");
		return mav;

	}

	@PostMapping("/create-new-user")
	public String createNewUser(@RequestParam String name, @RequestParam String password,
			@RequestParam(defaultValue = "ROLE_USER") String role) {
		userDetails.createNewUser(name, password, role);

		return "redirect:/";
	}

}
