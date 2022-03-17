package com.itechart.api_gateway.config;

import com.itechart.api_gateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig
{
    private final JwtAuthenticationFilter filter;

    @Autowired
    public GatewayConfig(JwtAuthenticationFilter filter)
    {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route("identity-service", r -> r.path("/identity/**")
                        .filters(f -> f.filter(filter)
                                .stripPrefix(1))
                        .uri("lb://identity-service"))
                .route("food-delivery", r -> r.path("/foodDelivery/**")
                        .filters(f -> f.filter(filter)
                                .stripPrefix(1))
                        .uri("lb://food-delivery"))
                .route("payment-service", r -> r.path("/payment/**")
                        .filters(f -> f.filter(filter)
                                .stripPrefix(1))
                        .uri("lb://payment-service"))
                .route("restaurant-info-service", r -> r.path("/restaurantInfo/**")
                        .filters(f -> f.filter(filter)
                                .stripPrefix(1))
                        .uri("lb://restaurant-info-service"))
                .route("courier-manager", r -> r.path("/courierManager/**")
                        .filters(f -> f.filter(filter)
                                .stripPrefix(1))
                        .uri("lb://courier-manager"))
                .build();
    }
}
