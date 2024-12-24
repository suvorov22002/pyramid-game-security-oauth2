package com.pyramid.tech;

import com.pyramid.tech.core.auth.RsaKeyConfigProperties;
import com.pyramid.tech.domain.registration.model.AppUser;
import com.pyramid.tech.domain.registration.model.enums.Role;
import com.pyramid.tech.domain.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableConfigurationProperties(RsaKeyConfigProperties.class)
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

	//@Bean
	CommandLineRunner initializeUser(UserRepository userRepository, @Qualifier("userPassWordEncoder") PasswordEncoder encoder) {
		return args -> {

			AppUser user = new AppUser();
			user.setRole(Role.ADMIN);
			user.setUsername("adminuser");
			user.setPassword(encoder.encode("adminuser"));
			//user.setId(2L);

			userRepository.save(user);
		};
	}

}
