package com.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class MicroserviceServicesDiscovery1Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceServicesDiscovery1Application.class, args);
	}

}
