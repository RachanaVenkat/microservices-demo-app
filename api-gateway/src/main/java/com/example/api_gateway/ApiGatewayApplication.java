package com.example.api_gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder,
                                     @Value("${user.service.url}") String userServiceUrl,
                                     @Value("${product.service.url}") String productServiceUrl,
                                     @Value("${order.service.url}") String orderServiceUrl,
                                     @Value("${payment.service.url}") String paymentServiceUrl) {
        return builder.routes()
                // User Service Routes
                .route("user-service", r -> r.path("/user-service/v3/api-docs").and().method(HttpMethod.GET).uri(userServiceUrl))
                .route("user-service-api", r -> r.path("/api/v1/user/**").uri(userServiceUrl))

                // Product Service Routes
                .route("product-service", r -> r.path("/product-service/v3/api-docs").and().method(HttpMethod.GET).uri(productServiceUrl))
                .route("product-service-api", r -> r.path("/api/v1/product/**").uri(productServiceUrl))

                // Order Service Routes
                .route("order-service", r -> r.path("/order-service/v3/api-docs").and().method(HttpMethod.GET).uri(orderServiceUrl))
                .route("order-service-api", r -> r.path("/api/v1/orders/**").uri(orderServiceUrl))

                // Payment Service Routes
                .route("payment-service", r -> r.path("/payment-service/v3/api-docs").and().method(HttpMethod.GET).uri(paymentServiceUrl))
                .route("payment-service-api", r -> r.path("/api/v1/payments/**").uri(paymentServiceUrl))

                .build();
    }
}
