package com.itechart.payment_service.controller;

import com.itechart.payment_service.dto.PaymentInfoDto;
import com.itechart.payment_service.dto.PaymentReceiptDto;
import com.itechart.payment_service.exception.OrderNotFoundException;
import com.itechart.payment_service.exception.PaymentException;
import com.itechart.payment_service.exception.PaymentProviderNotFoundException;
import com.itechart.payment_service.exception.PaymentReceiptNotFoundException;
import com.itechart.payment_service.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/paymentReceipt")
    public ResponseEntity<PaymentReceiptDto> getPaymentReceipt(@RequestParam("orderId") Long orderId)
            throws PaymentReceiptNotFoundException {
        return ResponseEntity.ok().body(paymentService.getPaymentReceipt(orderId));
    }

    @PostMapping("/orderPayment")
    public ResponseEntity<String> payForOrder(@RequestBody @Valid PaymentInfoDto paymentInfoDto)
            throws PaymentException, OrderNotFoundException,
            PaymentReceiptNotFoundException, PaymentProviderNotFoundException, IllegalArgumentException {
        paymentService.payForOrder(paymentInfoDto);
        return new ResponseEntity<>("Payment is approved", HttpStatus.OK);
    }
}
