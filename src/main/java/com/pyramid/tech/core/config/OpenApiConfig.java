package com.pyramid.tech.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 14:21
 * Project Name: pyramid-game-security-oauth2
 */

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Suvorov Vassilievitch",
                        email = "contact@vassilievitch.com",
                        url = "https://pyramid-distribution.com/games"
                ),
                description = "OpenApi documentation for Spring Security",
                title = "OpenApi specification - PYRAMID GAMES",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://pyramid-distribution.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8180"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://pyramid-distribution.com"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
