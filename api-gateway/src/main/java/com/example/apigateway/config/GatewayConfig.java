package com.example.apigateway.config;

import com.example.apigateway.filter.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthFilter authFilter;

    public GatewayConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://auth-service")

                )
                .route("message-service",r->r.path("/message/**")
                        .filters(f->f.filter(authFilter))
                        .uri("lb://message-service")
                ).build();
    }
}
