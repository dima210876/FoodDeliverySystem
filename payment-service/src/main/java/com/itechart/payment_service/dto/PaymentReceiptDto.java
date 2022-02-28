package com.itechart.payment_service.dto;

import lombok.Data;
import java.util.Set;

@Data
public class PaymentReceiptDto
{
    private Long id;
    private Long orderId;
    private String receiptStatus;
    private Set<PaymentAttemptDto> paymentAttempts;
}
