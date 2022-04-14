package com.itechart.payment_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentReceiptNotFoundException extends RuntimeException
{
    public PaymentReceiptNotFoundException(String message) { super(message); }
}
