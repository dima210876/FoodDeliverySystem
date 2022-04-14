package com.itechart.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfoDto
{
    @NotNull(message = "Order ID is required")
    @Min(value = 1L, message = "Order ID min limit exceeded")
    @Max(value = Long.MAX_VALUE, message = "Order ID max limit exceeded")
    private Long orderId;

    @NotNull(message = "Payment provider name is required")
    @NotBlank(message = "Payment provider name can't be empty")
    @Size(max = 100, message = "Payment provider name string length exceeded")
    private String paymentProviderName;

    @Size(max = 50, message = "Card number string length exceeded")
    private String cardNumber;

    @Size(max = 100, message = "Validity period string length exceeded")
    private String validityPeriod;

    @Size(max = 50, message = "Card code string length exceeded")
    private String cardCode;
}
