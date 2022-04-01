package com.itechart.payment_service.controller;

import com.itechart.payment_service.dto.PaymentInfoDto;
import com.itechart.payment_service.dto.PaymentReceiptDto;
import com.itechart.payment_service.exception.PaymentException;
import com.itechart.payment_service.exception.PaymentReceiptNotFoundException;
import com.itechart.payment_service.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class PaymentController
{
    private final PaymentService paymentService;

    @GetMapping("/paymentReceipt")
    public ResponseEntity<PaymentReceiptDto> getPaymentReceipt(@RequestParam("orderId") Long orderId)
            throws PaymentReceiptNotFoundException
    {
        return ResponseEntity.ok().body(paymentService.getPaymentReceipt(orderId));
    }

    @PostMapping("/orderPayment")
    public ResponseEntity<PaymentReceiptDto> payForOrder(@RequestBody @Valid PaymentInfoDto paymentInfoDto)
            throws PaymentException
    {
        return ResponseEntity.ok().body(paymentService.payForOrder(paymentInfoDto));
    }
}
