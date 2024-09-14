package com.wexad.spring_security_jwt.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@OpenAPIDefinition(
        info = @Info(
                title = "This is my project",
                description = "You can only register and sign in",
                version = "v0.0.1",
                contact = @Contact(name = "wexad", email = "sherzodchoriyev747@gmail.com")
        ),
        security = {@SecurityRequirement(name = "bearerAuth")},
        servers = {
                @Server(url = "http://localhost:8080", description = "For Local Mode")
        }
)

@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        description = "You have to add JWT Token",
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)

public class OpenApiConfig {
}
