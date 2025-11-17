package com.example.calculator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Calculator API - CI/CD Exercise")
                        .version("1.0.0")
                        .description("Simple calculator REST API for demonstrating CI/CD pipelines")
                        .contact(new Contact()
                                .name("CI/CD Workshop")
                                .email("workshop@example.com")));
    }
}
