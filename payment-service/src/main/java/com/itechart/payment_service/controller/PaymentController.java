package com.itechart.payment_service.controller;

import com.itechart.payment_service.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController
{
    private final PaymentService paymentService;

}
