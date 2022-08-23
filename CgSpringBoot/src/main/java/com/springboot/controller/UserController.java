package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.User;
import com.springboot.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
private	UserRepository  userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@PostMapping("/user")
	public User postUser(@RequestBody User user) {
		String passEncoded = passwordEncoder.encode(user.getPassword());
		user.setPassword(passEncoded);
		return userRepository.save(user);
	}
}
