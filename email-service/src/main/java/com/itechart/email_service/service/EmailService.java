package com.itechart.email_service.service;

import com.itechart.email_service.dto.ConfirmationInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService
{
    private final JavaMailSender emailSender;

    public void send(String to, String subject, String text)
    {
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try
        {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom("FoodDeliverySystem");
            this.emailSender.send(message);
        }
        catch (MessagingException messageException)
        {
            throw new RuntimeException(messageException);
        }
    }

    public void sendRegistrationSuccessfulMessage(String email)
    {
        String subject = "Registration in Food Delivery System";
        String text = "You are successfully registered in the system. Wish you the best user experience :)";
        send(email, subject, text);
    }

    public void sendRegistrationConfirmationMessage(ConfirmationInfoDto confirmationInfoDto)
    {
        String subject = "Registration in Food Delivery System";
        //TODO: replace localhost with gateway IP address
        String text = "You account has been created. To activate it visit this page: http://localhost:8085/identity/confirm/" +
                confirmationInfoDto.getConfirmationToken();
        send(confirmationInfoDto.getEmail(), subject, text);
    }
}
