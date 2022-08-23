package com.springboot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.repository.UserRepository;

@RestController
public class MainController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/hello/public")
	public String publicHello(){
		return"hello public spring";
}
	
	@GetMapping("/hello/private")
	public String protectedHello(){
		return"hello protected spring";
}
	@GetMapping("/hello")
	public String SayHello(){
	return"hello spring";
	}
	
	@PostMapping("/user")
	public User Postuser(@RequestBody User user) {
		String passwordEncoded=passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordEncoded);
		return userRepository.save(user);
		
		
	}
	
	
}
