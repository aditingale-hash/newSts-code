package com.springboot.security.microservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@GetMapping("/hello")
	public String helloOrderApi() {
		return "hello from order";
		
	}
	
	
}
