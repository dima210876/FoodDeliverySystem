package com.itechart.identity_service.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Role implements GrantedAuthority {
    ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_COURIER("ROLE_COURIER"),
    ROLE_MANAGER("ROLE_MANAGER");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
