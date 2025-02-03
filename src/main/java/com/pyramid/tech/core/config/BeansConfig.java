package com.pyramid.tech.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 24/01/2025
 * Time: 11:56
 * Project Name: pyramid-game-security-oauth2
 */

//@Configuration
public class BeansConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedHeader("*");
    //    cors.addAllowedMethod("*");
        cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        cors.setAllowCredentials(Boolean.TRUE);
        cors.addAllowedOrigin("http://127.0.0.1:6300");
        source.registerCorsConfiguration("/**", cors);

        return source;
    }
}
