package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.health.model.Doctor;
import com.health.model.User;
import com.health.repository.DoctorRepository;
import com.health.repository.UserRepository;



@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class UserController {
	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private DoctorRepository doctorRepository;


	@PostMapping("/user/{did}")
	public User postDoctor(@PathVariable("did") Long did,@RequestBody User user) {
		Doctor doctor=doctorRepository.getById(did);
		//String passEncoded = passwordEncoder.encode(user.getPassword());
		user.setDoctor(doctor);
		//user.setPassword(passEncoded);
		return userRepository.save(user);
	}

	@PostMapping("/user")
	public User postUser(@RequestBody User user) {
		//String passEncoded = passwordEncoder.encode(user.getPassword());
		//user.setPassword(passEncoded);
		return userRepository.save(user);
	}
	
	@GetMapping("/getalluser")
	public List<User> getalluser(){
		
		return userRepository.findAll();
		
	}
	
}
