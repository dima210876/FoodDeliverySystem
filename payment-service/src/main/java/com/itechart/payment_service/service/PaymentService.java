package com.itechart.payment_service.service;

import com.itechart.payment_service.repository.PaymentAttemptRepository;
import com.itechart.payment_service.repository.PaymentProviderRepository;
import com.itechart.payment_service.repository.PaymentReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService
{
    private final PaymentReceiptRepository paymentReceiptRepository;
    private final PaymentProviderRepository paymentProviderRepository;
    private final PaymentAttemptRepository paymentAttemptRepository;

}
