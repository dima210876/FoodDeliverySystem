package com.itechart.payment_service.repository;

import com.itechart.payment_service.model.PaymentReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentReceiptRepository extends JpaRepository<PaymentReceipt, Long>
{
    Optional<PaymentReceipt> findByOrderId(Long orderId);
}
