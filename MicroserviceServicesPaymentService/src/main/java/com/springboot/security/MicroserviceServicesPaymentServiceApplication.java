package com.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class MicroserviceServicesPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceServicesPaymentServiceApplication.class, args);
	}

}
