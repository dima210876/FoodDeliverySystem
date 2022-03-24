package com.itechart.identity_service.service;

import com.itechart.identity_service.config.RegistrationMessagingConfig;
import com.itechart.identity_service.model.RegistrationConfirmationToken;
import com.itechart.identity_service.model.User;
import com.itechart.identity_service.repository.RegistrationConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class RegistrationConfirmationTokenService
{
    private final RegistrationConfirmationTokenRepository registrationConfirmationTokenRepository;
    private final RabbitTemplate rabbitTemplate;

    public RegistrationConfirmationToken createRegistrationConfirmationToken(User createdUser)
    {
        RegistrationConfirmationToken savedToken = registrationConfirmationTokenRepository.save(
                RegistrationConfirmationToken.builder()
                        .user(createdUser)
                        .confirmationToken(UUID.randomUUID().toString().replace("-", ""))
                        .build()
        );
        rabbitTemplate.convertAndSend(
                RegistrationMessagingConfig.EXCHANGE,
                RegistrationMessagingConfig.ROUTING_KEY,
                savedToken
        );
        return savedToken;
    }


}
