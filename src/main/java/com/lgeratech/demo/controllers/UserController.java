package com.lgeratech.demo.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lgeratech.demo.models.User;
import com.lgeratech.demo.repositories.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value = "/")
	public String index() {
		return "home";
	}	
	
	@GetMapping(value = "/home")
	public String home() {
		return "home";
	}	
	
	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}	
	
	@GetMapping(value = "/registration")
	public String registration() {
		return "registration";
	}	
	
	@PostMapping(value = "/addUser")
	public String createUser(@ModelAttribute User user) throws Exception{
		try {
			user.setActive(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return "login";
	}
	
	@GetMapping(value = "/profile")
	public String profile(Model model, Principal principal) {
		Optional<User> currentUser = userRepository.findByUserName(principal.getName());
		model.addAttribute("user", currentUser.get());
		return "profile";
	}	

}
