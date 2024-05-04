package com.shopping.application.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Gateway {
    @Bean
     RouteLocator gatewayRoute(RouteLocatorBuilder builder){
        return builder.routes().route("orderServiceRoute" ,
                predicateSpec -> predicateSpec.path("/api/order").uri("lb://order-service")).
            route("productServiceRoute" ,p-> p.path("/api/product").uri("lb://product-service"))
                .route("inventoryServiceRoute" , p->p.path("/api/inventory").uri("lb://inventory-service"))
        .build();
    }


}
