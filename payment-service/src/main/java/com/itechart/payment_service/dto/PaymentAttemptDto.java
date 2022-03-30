package com.itechart.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAttemptDto
{
    private Long id;

    @NotNull(message = "Payment receipt id is required")
    private PaymentReceiptDto paymentReceipt;

    @NotNull(message = "Payment provider id is required")
    private PaymentProviderDto paymentProvider;

    @Size(min = 10, max = 50, message = "Transaction number string length limits exceeded")
    private String transactionNumber;

    @NotNull(message = "Payment status is required")
    @NotBlank(message = "Payment status can't be empty")
    @Size(min = 2, max = 20, message = "Payment status string length limits exceeded")
    private String paymentStatus;

    @NotNull(message = "Payment datetime is required")
    @NotBlank(message = "Payment datetime can't be empty")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime paymentDatetime;
}
