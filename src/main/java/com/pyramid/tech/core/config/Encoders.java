package com.pyramid.tech.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 16:32
 * Project Name: pyramid-game-security-oauth2
 */
@Configuration
public class Encoders {

    @Bean
    @Primary
    public PasswordEncoder userPassWordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder oauthClientPassWordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

}
