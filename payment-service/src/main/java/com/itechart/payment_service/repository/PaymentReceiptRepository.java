package com.itechart.payment_service.repository;

import com.itechart.payment_service.model.PaymentReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentReceiptRepository extends JpaRepository<PaymentReceipt, Long> { }
