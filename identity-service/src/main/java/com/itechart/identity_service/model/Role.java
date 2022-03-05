package com.itechart.identity_service.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Role implements GrantedAuthority {
    CUSTOMER("CUSTOMER"),
    MANAGER("MANAGER");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
