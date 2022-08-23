package com.health.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@EntityScan(basePackages = {"com.health.model"})  
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Hospital1Application {

	public static void main(String[] args) {
		SpringApplication.run(Hospital1Application.class, args);
	}

}
