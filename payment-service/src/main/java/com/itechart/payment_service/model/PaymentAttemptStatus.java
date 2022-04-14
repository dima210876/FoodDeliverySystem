package com.itechart.payment_service.model;

public enum PaymentAttemptStatus
{
    CONFIRMED("confirmed"),
    REJECTED("rejected");

    private final String status;

    PaymentAttemptStatus(String status) { this.status = status; }

    public String getStatus() { return status; }
}
