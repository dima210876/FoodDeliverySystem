package com.itechart.api_gateway.filter;

import com.itechart.api_gateway.exception.JwtTokenMalformedException;
import com.itechart.api_gateway.exception.JwtTokenMissingException;
import com.itechart.api_gateway.model.Role;
import com.itechart.api_gateway.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;
import io.jsonwebtoken.Claims;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter implements GatewayFilter
{
    private final JwtUtil jwtUtil;
    private final List<String> generalEndpoints = List.of("/identity/register", "/identity/login", "/identity/confirm", "/foodDelivery/menu");
    private final List<String> customerEndpoints = List.of("/foodDelivery", "/payment");
    private final List<String> restaurantManagerEndpoints = List.of("/restaurantInfo");
    private final List<String> courierEndpoints = List.of("/courierManager/courier", "/foodDelivery", "/payment");
    private final List<String> courierManagerEndpoints = List.of("/courierManager/company", "/courierManager/courier");
    private final List<String> superAdminEndpoints = List.of("/identity/users", "/foodDelivery", "/restaurantInfo",
            "/courierManager", "/restaurantInfo");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        ServerHttpRequest request = exchange.getRequest();

        Predicate<ServerHttpRequest> isApiSecured = r -> generalEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request))
        {
            if (!request.getHeaders().containsKey("Authorization"))
            {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);
            try { jwtUtil.validateToken(token); }
            catch (JwtTokenMalformedException | JwtTokenMissingException e)
            {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                return response.setComplete();
            }

            String userRole = String.valueOf(jwtUtil.getClaims(token).get("role"));
            String path = request.getURI().getPath();
            boolean haveAccess = false;
            Role role = Role.valueOf(userRole);
            switch (role)
            {
                case ROLE_CUSTOMER:
                    haveAccess = checkAccess(customerEndpoints, path);
                    break;
                case ROLE_MANAGER:
                    haveAccess = checkAccess(restaurantManagerEndpoints, path);
                    break;
                case ROLE_COURIER:
                    haveAccess = checkAccess(courierEndpoints, path);
                    break;
                case ROLE_COURIER_SERVICE_MANAGER:
                    haveAccess = checkAccess(courierManagerEndpoints, path);
                    break;
                case ROLE_SUPER_ADMIN:
                    haveAccess = checkAccess(superAdminEndpoints, path);
                    break;
                default:
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.BAD_REQUEST);
                    return response.setComplete();
            }
            if (!haveAccess)
            {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            populateRequestWithHeaders(exchange, token);
        }
        return chain.filter(exchange);
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token)
    {
        Claims claims = jwtUtil.getClaims(token);
        exchange.getRequest().mutate()
                .header("userId", String.valueOf(claims.get("userId")))
                .header("email", String.valueOf(claims.get("email")))
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }

    private boolean checkAccess(List<String> endpoints, String path)
    {
        return endpoints.stream().anyMatch(path::contains);
    }
}
