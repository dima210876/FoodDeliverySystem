package com.itechart.payment_service.service;

import com.itechart.payment_service.dto.*;
import com.itechart.payment_service.exception.OrderNotFoundException;
import com.itechart.payment_service.exception.PaymentException;
import com.itechart.payment_service.exception.PaymentProviderNotFoundException;
import com.itechart.payment_service.exception.PaymentReceiptNotFoundException;
import com.itechart.payment_service.model.*;
import com.itechart.payment_service.repository.PaymentAttemptRepository;
import com.itechart.payment_service.repository.PaymentProviderRepository;
import com.itechart.payment_service.repository.PaymentReceiptRepository;
import com.itechart.payment_service.util.ElectronicPaymentSystem;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentService
{
    private final PaymentReceiptRepository paymentReceiptRepository;
    private final PaymentProviderRepository paymentProviderRepository;
    private final PaymentAttemptRepository paymentAttemptRepository;
    private final ElectronicPaymentSystem electronicPaymentSystem;
    private final RestTemplate restTemplate;

    public PaymentReceiptDto getPaymentReceipt(Long orderId) throws PaymentReceiptNotFoundException
    {
        Optional<PaymentReceipt> optionalPaymentReceipt = paymentReceiptRepository.findByOrderId(orderId);
        if (optionalPaymentReceipt.isEmpty()) {
            throw new PaymentReceiptNotFoundException("Payment receipt for order with given order ID not found.");
        }
        return convertToDto(optionalPaymentReceipt.get());
    }

    @Transactional
    public PaymentReceiptDto payForOrder(@Valid PaymentInfoDto paymentInfoDto) throws PaymentException, OrderNotFoundException,
            PaymentReceiptNotFoundException, PaymentProviderNotFoundException, IllegalArgumentException
    {
        Long orderId = paymentInfoDto.getOrderId();
        final String GET_ORDER_URL = "http://FOOD-DELIVERY/order/" + orderId;
        final String POST_CHANGE_ORDER_STATUS_URL = "http://FOOD-DELIVERY/changeOrderStatus/" + orderId;

        ResponseEntity<OrderDto> responseEntity = restTemplate.getForEntity(GET_ORDER_URL, OrderDto.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new OrderNotFoundException("Order not found.");
        }
        OrderDto orderDto = responseEntity.getBody();
        if (orderDto == null) {
            throw new OrderNotFoundException("Order not found.");
        }
        Optional<PaymentReceipt> optionalPaymentReceipt = paymentReceiptRepository.findByOrderId(orderId);
        PaymentReceipt paymentReceipt;
        if (optionalPaymentReceipt.isEmpty()) {
            paymentReceipt = PaymentReceipt.builder()
                    .orderId(orderDto.getId())
                    .receiptStatus(PaymentReceiptStatus.NOT_PAID.getStatus())
                    .build();
            paymentReceipt = paymentReceiptRepository.saveAndFlush(paymentReceipt);
        }
        else {
            paymentReceipt = optionalPaymentReceipt.get();
        }

        if (paymentReceipt.getReceiptStatus().equals(PaymentReceiptStatus.PAID.getStatus())) {
            throw new PaymentException("Order has already been paid for.");
        }

        Optional<PaymentProvider> optionalPaymentProvider =
                paymentProviderRepository.findByName(paymentInfoDto.getPaymentProviderName());
        if (optionalPaymentProvider.isEmpty()) {
            throw new PaymentException("Payment provider not found.");
        }
        PaymentProvider paymentProvider = optionalPaymentProvider.get();
        String paymentMethod = paymentProvider.getPaymentMethod();
        PaymentAttempt paymentAttempt;

        // the discount field contains a discount percentage on the order
        // ((100 - OrderDto.GetDiscount()) / 100 represents the percentage of the order cost after subtraction the discount percentage
        Double orderTotalPrice =
                orderDto.getOrderPrice() * (100 - orderDto.getDiscount()) / 100 + orderDto.getShippingPrice();
        PaymentMethod method = PaymentMethod.valueOf(paymentMethod.toUpperCase());

        switch(method)
        {
            case PHYSICAL:
                 paymentAttempt = PaymentAttempt.builder()
                        .paymentReceipt(paymentReceipt)
                        .paymentProvider(paymentProvider)
                        .transactionNumber("-")
                        .paymentStatus(PaymentAttemptStatus.CONFIRMED.getStatus())
                        .paymentDatetime(LocalDateTime.now())
                        .build();
                paymentAttemptRepository.save(paymentAttempt);
                paymentReceipt.setReceiptStatus(PaymentReceiptStatus.PAID.getStatus());
                break;

            case ELECTRONIC:
                if (!electronicPaymentSystem.executePayment(
                        paymentInfoDto.getCardNumber(),
                        paymentInfoDto.getValidityPeriod(),
                        paymentInfoDto.getCardCode(),
                        orderTotalPrice))
                {
                     paymentAttempt = PaymentAttempt.builder()
                            .paymentReceipt(paymentReceipt)
                            .paymentProvider(paymentProvider)
                            .paymentStatus(PaymentAttemptStatus.REJECTED.getStatus())
                            .paymentDatetime(LocalDateTime.now())
                            .build();
                    paymentAttemptRepository.save(paymentAttempt);
                    throw new PaymentException("Error while trying to pay for the order.");
                }
                paymentAttempt = PaymentAttempt.builder()
                        .paymentReceipt(paymentReceipt)
                        .paymentProvider(paymentProvider)
                        .transactionNumber(electronicPaymentSystem.getTransactionNumber())
                        .paymentStatus(PaymentAttemptStatus.CONFIRMED.getStatus())
                        .paymentDatetime(LocalDateTime.now())
                        .build();
                paymentAttemptRepository.save(paymentAttempt);
                paymentReceipt.setReceiptStatus(PaymentReceiptStatus.PAID.getStatus());
                break;
            default:
                throw new PaymentException("Payment method not found.");
        }

        ResponseEntity<OrderDto> response = restTemplate
                .postForEntity(POST_CHANGE_ORDER_STATUS_URL, OrderStatus.PAID.getStatus(), OrderDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new PaymentException("Can't update order status after payment.");
        }

        return convertToDto(paymentReceipt);
    }

    private PaymentProviderDto convertToDto(PaymentProvider paymentProvider)
    {
        return PaymentProviderDto.builder()
                .id(paymentProvider.getId())
                .name(paymentProvider.getName())
                .paymentMethod(paymentProvider.getPaymentMethod())
                .status(paymentProvider.getStatus())
                .description(paymentProvider.getDescription())
                .build();
    }

    private PaymentAttemptDto convertToDto(PaymentAttempt paymentAttempt)
    {
        return PaymentAttemptDto.builder()
                .id(paymentAttempt.getId())
                .paymentProvider(convertToDto(paymentAttempt.getPaymentProvider()))
                .transactionNumber(paymentAttempt.getTransactionNumber())
                .paymentStatus(paymentAttempt.getPaymentStatus())
                .paymentDatetime(paymentAttempt.getPaymentDatetime())
                .build();
    }

    private PaymentReceiptDto convertToDto(PaymentReceipt paymentReceipt)
    {
        return PaymentReceiptDto.builder()
                .id(paymentReceipt.getId())
                .orderId(paymentReceipt.getOrderId())
                .receiptStatus(paymentReceipt.getReceiptStatus())
                .paymentAttempts(paymentReceipt.getPaymentAttempts().stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
