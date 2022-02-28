package com.itechart.payment_service.repository;

import com.itechart.payment_service.model.PaymentAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentAttemptRepository extends JpaRepository<PaymentAttempt, Long> { }
