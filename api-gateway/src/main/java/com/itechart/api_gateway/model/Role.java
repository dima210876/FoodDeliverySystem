package com.itechart.api_gateway.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role
{
    ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_COURIER("ROLE_COURIER"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_COURIER_SERVICE_MANAGER("ROLE_COURIER_SERVICE_MANAGER"),
    ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN");

    private final String value;

    public String getAuthority() { return value; }
}
