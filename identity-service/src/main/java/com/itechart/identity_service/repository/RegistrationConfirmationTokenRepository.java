package com.itechart.identity_service.repository;

import com.itechart.identity_service.model.RegistrationConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegistrationConfirmationTokenRepository extends JpaRepository<RegistrationConfirmationToken, Long>
{
   Optional<RegistrationConfirmationToken> findByConfirmationToken(String confirmationToken);
}
