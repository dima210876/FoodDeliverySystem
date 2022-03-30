package com.itechart.payment_service.service;

import com.itechart.payment_service.dto.OrderDto;
import com.itechart.payment_service.dto.PaymentInfoDto;
import com.itechart.payment_service.dto.PaymentReceiptDto;
import com.itechart.payment_service.exception.PaymentException;
import com.itechart.payment_service.model.PaymentAttempt;
import com.itechart.payment_service.model.PaymentProvider;
import com.itechart.payment_service.model.PaymentReceipt;
import com.itechart.payment_service.repository.PaymentAttemptRepository;
import com.itechart.payment_service.repository.PaymentProviderRepository;
import com.itechart.payment_service.repository.PaymentReceiptRepository;
import com.itechart.payment_service.util.ElectronicPaymentSystem;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService
{
    private final PaymentReceiptRepository paymentReceiptRepository;
    private final PaymentProviderRepository paymentProviderRepository;
    private final PaymentAttemptRepository paymentAttemptRepository;
    private final ElectronicPaymentSystem electronicPaymentSystem;
    private final RestTemplate restTemplate;

    public PaymentReceiptDto payForOrder(PaymentInfoDto paymentInfoDto) throws PaymentException
    {
        Long orderId = paymentInfoDto.getOrderId();
        final String GET_ORDER_URL = "http://FOOD-DELIVERY/order/" + orderId.toString();

        ResponseEntity<OrderDto> responseEntity = restTemplate.getForEntity(GET_ORDER_URL, OrderDto.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new PaymentException("Order not found.");
        }
        OrderDto orderDto = responseEntity.getBody();
        if (orderDto == null) {
            throw new PaymentException("Order not found.");
        }
        Optional<PaymentReceipt> optionalPaymentReceipt = paymentReceiptRepository.findByOrderId(orderId);
        PaymentReceipt paymentReceipt;
        if (optionalPaymentReceipt.isEmpty()) {
            paymentReceipt = PaymentReceipt.builder()
                    .orderId(orderDto.getId())
                    .receiptStatus("not paid")
                    .build();
            paymentReceipt = paymentReceiptRepository.saveAndFlush(paymentReceipt);
        }
        else {
            paymentReceipt = optionalPaymentReceipt.get();
        }

        Optional<PaymentProvider> optionalPaymentProvider =
                paymentProviderRepository.findByName(paymentInfoDto.getPaymentProviderName());
        if (optionalPaymentProvider.isEmpty()) {
            throw new PaymentException("Payment provider not found.");
        }
        PaymentProvider paymentProvider = optionalPaymentProvider.get();
        String paymentMethod = paymentProvider.getPaymentMethod();

        Double orderTotalPrice =
                orderDto.getOrderPrice() * (100 - orderDto.getDiscount()) / 100 + orderDto.getShippingPrice();

        switch(paymentMethod)
        {
            case "physical":

                break;
            case "electronic":

                if (!electronicPaymentSystem.executePayment(
                        paymentInfoDto.getCardNumber(),
                        paymentInfoDto.getValidityPeriod(),
                        paymentInfoDto.getCardCode(),
                        orderTotalPrice))
                {
                    PaymentAttempt paymentAttempt = PaymentAttempt.builder()
                            .paymentReceipt(paymentReceipt)
                            .paymentProvider(optionalPaymentProvider.get())
                            .build();
                    throw new PaymentException("Error while trying to pay for the order.");
                }

                break;
            default:
                throw new PaymentException("Payment method not found.");
        }





    }



}
