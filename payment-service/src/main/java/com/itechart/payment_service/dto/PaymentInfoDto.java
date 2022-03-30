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
    @NotBlank(message = "Order ID can't be empty")
    @Min(value = 1L, message = "Order ID min limit exceeded")
    @Max(value = Long.MAX_VALUE, message = "Order ID max limit exceeded")
    private Long orderId;

    @NotNull(message = "Payment provider name is required")
    @NotBlank(message = "Payment provider name can't be empty")
    @Size(max = 100, message = "Payment provider name string length exceeded")
    private String paymentProviderName;

//    @NotNull(message = "Card number is required")
//    @NotBlank(message = "Card number can't be empty")
    @Size(max = 50, message = "Card number string length exceeded")
    private String cardNumber;

//    @NotNull(message = "Validity period is required")
//    @NotBlank(message = "Validity period can't be empty")
    @Size(max = 20, message = "Validity period string length exceeded")
    private String validityPeriod;

//    @NotNull(message = "Card code is required")
//    @NotBlank(message = "Card code can't be empty")
    @Size(max = 50, message = "Card code string length exceeded")
    private String cardCode;
}
