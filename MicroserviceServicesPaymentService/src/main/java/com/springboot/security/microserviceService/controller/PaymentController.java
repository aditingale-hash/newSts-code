package com.springboot.security.microserviceService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class PaymentController {


	@GetMapping("/hello/payment")
	public String helloPaymentApi() {
		return "hello from Payment";
		
	}
}
