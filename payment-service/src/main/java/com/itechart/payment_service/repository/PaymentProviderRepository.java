package com.itechart.payment_service.repository;

import com.itechart.payment_service.model.PaymentProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentProviderRepository extends JpaRepository<PaymentProvider, Long>
{
    Optional<PaymentProvider> findByName(String name);
}
