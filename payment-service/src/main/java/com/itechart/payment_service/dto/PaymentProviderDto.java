package com.itechart.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProviderDto
{
    private Long id;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(max = 100, message = "Name string length exceeded")
    private String name;

    @NotNull(message = "Status is required")
    @NotBlank(message = "Status can't be empty")
    @Size(max = 20, message = "Status string length exceeded")
    private String status;

    @Size(max = 200, message = "Description string length exceeded")
    private String description;
}
