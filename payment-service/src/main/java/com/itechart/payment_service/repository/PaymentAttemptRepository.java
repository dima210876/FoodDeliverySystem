package com.itechart.payment_service.repository;

import com.itechart.payment_service.model.PaymentAttempt;
import com.itechart.payment_service.model.PaymentReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PaymentAttemptRepository extends JpaRepository<PaymentAttempt, Long>
{
    List<PaymentAttempt> findAllByPaymentReceipt(PaymentReceipt paymentReceipt);
}
