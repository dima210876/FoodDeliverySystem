package com.itechart.food_delivery.model;

public enum OrderStatus
{
    NOT_PAID("not_paid"),
    PAID("paid"),
    VERIFICATION("verification"),
    COOKING("cooking"),
    READY("ready"),
    DELIVERING("delivering"),
    DELIVERED("delivered");

    private final String status;

    OrderStatus(String status) { this.status = status; }

    public String getStatus() { return status; }
}
