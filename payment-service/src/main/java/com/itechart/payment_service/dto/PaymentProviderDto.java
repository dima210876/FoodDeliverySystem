package com.itechart.payment_service.dto;

import lombok.Data;

@Data
public class PaymentProviderDto
{
    private Long id;
    private String name;
    private String status;
    private String description;
}
