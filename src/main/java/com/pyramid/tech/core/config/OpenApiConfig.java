package com.pyramid.tech.core.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
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
                    .title("Pyramid API Authorization")
                    .version("1.0.0")
                    .description("API description")
                    .contact(new Contact()
                            .name("Vassilievitch SUVOROV")
                            .email("suvorov22002@gmail.com")
                            .url("https://api.pyramid-distributions.cm"))
                    .license(new License()
                            .name("Apache 2.0")
                            .url("http://springdoc.org")))
                .addServersItem(new Server().url("http://127.0.0.1:6300").description("Development server"))
                .addServersItem(new Server().url("http://192.168.56.2:6300").description("Production server"))
                .components(new Components()
                    .addSecuritySchemes("bearerAuth", new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));

    }
}
