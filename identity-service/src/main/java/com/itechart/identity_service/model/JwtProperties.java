package com.itechart.identity_service.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:security.properties")
@Data
public class JwtProperties {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.accessTokenExpirationInMilliseconds}")
    private long accessTokenExpirationInMilliseconds;
}
