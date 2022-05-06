package com.example.demo;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Route Config
 */
@Configuration
public class Config {

    /**
     * Definition of path pattern. All request is routing target.
     */
    final private String ALL_MATCH_URI_PATTERN = "/**";

    final private String targetRouteUri = "http://localhost:50080";

    final private String otherRouteUri = "http://localhost:60080";

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder,
                               RequestBodyPredicateFactory requestBodyFiilterFactory) {
        return builder.routes()
                .route("target", r -> r.path(ALL_MATCH_URI_PATTERN)
                        .and().method("POST")
                        .and().readBody(String.class, requestBody -> true)
                        .and().predicate(requestBodyFiilterFactory.apply(new RequestBodyPredicateFactory.Config()))
                        .uri(targetRouteUri)
                )
                .route("other", r -> r.path(ALL_MATCH_URI_PATTERN)
                        .uri(otherRouteUri)
                )
                .build();
    }
}
