package com.example.demo;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/**
 * custom predicate
 */
@Component
public class RequestBodyPredicateFactory extends AbstractRoutePredicateFactory<RequestBodyPredicateFactory.Config> {

    final static private String TARGET_BODY_STRING = "target-request-body";

    /**
     * constructor
     */
    public RequestBodyPredicateFactory() {
        super(RequestBodyPredicateFactory.Config.class);
    }

    /**
     * main predicate method.
     * @param config config class
     * @return Whether request is target or not.
     */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return (ServerWebExchange exchange) -> {
            String requestBody = exchange.getAttribute("cachedRequestBodyObject");
            System.out.println("requestBody: " + requestBody);
            return config.returnInverse ? !isTarget(requestBody) : isTarget(requestBody);
        };
    }

    /**
     * If request is target, "isTarget" return true.
     * @param requestBody request body string
     * @return isTarget
     */
    private static boolean isTarget(String requestBody) {
        return requestBody.equals(TARGET_BODY_STRING);
    }

    /**
     * If "returnInverse" is true, "apply" return inverse boolean.
     */
    static class Config {
        private boolean returnInverse;

        public Config() {
            returnInverse = false;
        }

        public Config(boolean returnInverse) {
            this.returnInverse = returnInverse;
        }
    }
}
