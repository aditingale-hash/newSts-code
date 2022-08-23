package com.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroserviceServicesDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceServicesDiscoveryApplication.class, args);
	}

}
