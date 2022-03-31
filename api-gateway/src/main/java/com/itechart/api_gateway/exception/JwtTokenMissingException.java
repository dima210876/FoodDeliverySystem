package com.itechart.api_gateway.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException
{
    public JwtTokenMissingException(String message)
    {
        super(message);
    }
}
