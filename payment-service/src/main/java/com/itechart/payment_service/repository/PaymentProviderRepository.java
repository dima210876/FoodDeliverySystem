package com.itechart.payment_service.repository;

import com.itechart.payment_service.model.PaymentProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentProviderRepository extends JpaRepository<PaymentProvider, Long> { }
