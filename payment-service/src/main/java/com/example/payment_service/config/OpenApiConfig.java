package com.example.payment_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Payment Service API", version = "1.0", description = "API documentation for Payment Service")
)
@Configuration
public class OpenApiConfig {
}
