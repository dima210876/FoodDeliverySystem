package com.itechart.identity_service.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.itechart.identity_service.dto.UserInfoDto;
import com.itechart.identity_service.model.JwtProperties;
import com.itechart.identity_service.model.LoginData;
import com.itechart.identity_service.model.User;
import com.itechart.identity_service.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;
    private final UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ObjectMapper mapper = new ObjectMapper();
        LoginData loginData = new LoginData();

        try {
            loginData = mapper.readValue(request.getInputStream(), LoginData.class);
        } catch (IOException e) {
            //TODO add logger: invalid login data
        }

        UserDetails user = userService.loadUserByUsername(loginData.getEmail());
        if (user == null) {
            throw new BadCredentialsException("1000");
        }
        if (!encoder.matches(loginData.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("1000");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword(), user.getAuthorities());
        System.out.println(authenticationToken);
        return authenticationToken;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = userService.loadUserByUsername(String.valueOf(authentication.getPrincipal()));
        UserInfoDto userInfo = UserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().getAuthority())
                .build();

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtProperties.getSecretKey());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        String accessToken = Jwts.builder()
                .claim("role", user.getAuthorities().toString())
                .claim("email", user.getEmail())
                .claim("userId", user.getId())
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(calculateExpirationDate(jwtProperties.getAccessTokenExpirationInMilliseconds()))
                .signWith(signatureAlgorithm, signingKey)
                .compact();

        response.setContentType(APPLICATION_JSON_VALUE);
        ServletOutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        JsonNode firstChildNode = mapper.valueToTree(userInfo);
        rootNode.set("user", firstChildNode);
        ObjectNode secondChildNode = rootNode.put("token", accessToken);
        mapper.writeValue(outputStream, secondChildNode);
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
