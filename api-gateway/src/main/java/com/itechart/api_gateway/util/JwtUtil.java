package com.itechart.api_gateway.util;

import com.itechart.api_gateway.exception.JwtTokenMalformedException;
import com.itechart.api_gateway.exception.JwtTokenMissingException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:security.properties")
public class JwtUtil
{
    @Value("${jwt.secretKey}")
    private String jwtSecret;

    public Claims getClaims(final String token)
    {
        try { return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody(); }
        catch (Exception e)
        {
            System.out.println(e.getMessage() + ": " + e);
        }
        return null;
    }

    public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException
    {
        try { Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token); }
        catch (SignatureException ex)
        {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        }
        catch (MalformedJwtException ex)
        {
            throw new JwtTokenMalformedException("Invalid JWT token");
        }
        catch (ExpiredJwtException ex)
        {
            throw new JwtTokenMalformedException("Expired JWT token");
        }
        catch (UnsupportedJwtException ex)
        {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        }
        catch (IllegalArgumentException ex)
        {
            throw new JwtTokenMissingException("JWT claims string is empty");
        }
    }
}
