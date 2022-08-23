package com.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.model.User;
import com.springboot.security.repository.UserRepository;

	@RestController
	public class MainController {
		@Autowired
		private UserRepository userRepository;

		@Autowired
		private PasswordEncoder passwordEncoder;
		
	@GetMapping("/hello")
	public String sayHello(){
		return "Hello user";
	}
	@GetMapping("/hello/private")
	public String sayPrivateHello() {
		return "Hello Private User";
	}
		@GetMapping("/hello/public")
		public String sayPublicHello() {
			return "Hello Public User";
	}
		@PostMapping("/user")
		public User postUser(@RequestBody User user) {
			String passEncoded = passwordEncoder.encode(user.getPassword());
			user.setPassword(passEncoded);
			return userRepository.save(user);
		}
	}
	
	