package com.security.UserAuthMicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserAuthMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthMicroservicesApplication.class, args);
	}
}