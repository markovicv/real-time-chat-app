package com.example.apigateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> openEndpoints = List.of("/auth/login","/auth/register");

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints.stream()
                                .noneMatch(uri->request.getURI().getPath().contains(uri));
}
