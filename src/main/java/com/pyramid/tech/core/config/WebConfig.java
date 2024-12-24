package com.pyramid.tech.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Suvorov Vassilievitch
 * Date: 24/12/2024
 * Time: 12:47
 * Project Name: pyramid-game-security-oauth2
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
        //        .allowedOriginPatterns("*")
                .allowedOrigins("http://127.0.0.1:6300", "http://192.168.56.2:6300")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
