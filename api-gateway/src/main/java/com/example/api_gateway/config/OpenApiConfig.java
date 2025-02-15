package com.example.api_gateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "API Gateway - Microservices",
                version = "1.0",
                contact = @Contact(
                        name = "Support Team",
                        url = "http://example.com",
                        email = "support@example.com"
                ),
                description = "API Gateway routes for User, Product, Order, and Payment services.",
                termsOfService = "Use under the provided conditions."
        )
)
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("API Gateway"),
                        new Server().url("http://localhost:8081").description("User Service"),
                        new Server().url("http://localhost:8082").description("Product Service"),
                        new Server().url("http://localhost:8083").description("Order Service"),
                        new Server().url("http://localhost:8084").description("Payment Service")
                ));
    }
}
