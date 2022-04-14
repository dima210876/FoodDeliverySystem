package com.itechart.payment_service.model;

public enum PaymentMethod
{
    PHYSICAL("physical"),
    ELECTRONIC("electronic");

    private final String method;

    PaymentMethod(String method) { this.method = method; }

    public String getMethod() { return method; }
}
