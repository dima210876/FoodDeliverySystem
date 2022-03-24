package com.itechart.email_service;

import com.itechart.email_service.dto.ConfirmationInfoDto;
import com.itechart.email_service.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class EmailServiceApplication
{
    private EmailService emailService;

    public static void main(String[] args)
    {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

    @RabbitListener(queues = "registration_confirmation_email_queue")
    public void consumeRegistrationConfirmationMessage(ConfirmationInfoDto confirmationInfoDto)
    {
        emailService.sendRegistrationConfirmationMessage(confirmationInfoDto);
    }

    @RabbitListener(queues = "registration_email_queue")
    public void consumeRegistrationMessage(String email)
    {
        emailService.sendRegistrationSuccessfulMessage(email);
    }
}
