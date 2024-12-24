package com.pyramid.tech.core.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 14:21
 * Project Name: pyramid-game-security-oauth2
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("OAuth2 API Authorization")
                        .version("1.0.0")
                        .description("")
                        .contact(new Contact()
                                .name("Vassilievitch")
                                .email("suvorov22002@gmail.com")
                                .url("https://"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                );
    }
}
