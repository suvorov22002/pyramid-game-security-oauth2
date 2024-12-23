package com.pyramid.tech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PyramidGameSecurityOauth2Application {


	public static void main(String[] args) {
		SpringApplication.run(PyramidGameSecurityOauth2Application.class, args);
	}

	//@Bean
	CommandLineRunner run(@Qualifier("userPassWordEncoder") PasswordEncoder encoder) {
		return args -> {
			String rawPassword = "suvorov2024@@";
			String encodePassword = encoder.encode(rawPassword);

			System.out.println("Encoded: " + encodePassword);
		};
	}

}
