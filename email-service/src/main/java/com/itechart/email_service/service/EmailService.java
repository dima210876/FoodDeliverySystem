package com.itechart.email_service.service;

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

    public void sendRegistrationMessage(String email)
    {
        String subject = "Registration in Food Delivery System";
        String text = "You are successfully registered in the system. Wish you the best user experience :)";
        send(email, subject, text);
    }
}
