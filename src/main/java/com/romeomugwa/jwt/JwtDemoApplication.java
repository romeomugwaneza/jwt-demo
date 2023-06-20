package com.romeomugwa.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtDemoApplication {

	public static void main(String[] args) {
		System.out.println("===Start===");
		SpringApplication.run(JwtDemoApplication.class, args);
		System.out.println("===End===");
	}

}
