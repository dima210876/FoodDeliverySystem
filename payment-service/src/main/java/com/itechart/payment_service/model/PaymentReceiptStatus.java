package com.itechart.payment_service.model;

public enum PaymentReceiptStatus
{
    NOT_PAID("not paid"),
    PAID("paid");

    private final String status;

    PaymentReceiptStatus(String status) { this.status = status; }

    public String getStatus() { return status; }
}
