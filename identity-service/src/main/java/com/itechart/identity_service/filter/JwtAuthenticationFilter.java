package com.itechart.identity_service.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.identity_service.model.JwtProperties;
import com.itechart.identity_service.model.LoginData;
import com.itechart.identity_service.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper mapper = new ObjectMapper();

        LoginData loginData = new LoginData();

        try {
            loginData = mapper.readValue(request.getInputStream(), LoginData.class);
        } catch (IOException e) {
            //TODO add logger: invalid login data
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes());
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(calculateExpirationDate(jwtProperties.getAccessTokenExpirationInMilliseconds()))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("role", user.getAuthorities())
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(calculateExpirationDate(jwtProperties.getRefreshTokenExpirationInMilliseconds()))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Authentication Failed");
    }

    private Date calculateExpirationDate(long expiration) {
        Date now = new Date();
        return new Date(now.getTime() + expiration);
    }
}
