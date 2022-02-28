package com.itechart.payment_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentAttemptDto
{
    private Long id;
    private PaymentReceiptDto paymentReceipt;
    private PaymentProviderDto paymentProvider;
    private String transactionNumber;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime paymentDatetime;
}
