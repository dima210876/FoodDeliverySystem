package com.itechart.identity_service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequestDTO implements Serializable {
    private String login;
    private String password;
}
