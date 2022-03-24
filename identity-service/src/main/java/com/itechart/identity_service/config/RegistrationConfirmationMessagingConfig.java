package com.itechart.identity_service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistrationConfirmationMessagingConfig
{
    public static final String QUEUE = "registration_confirmation_email_queue";
    public static final String EXCHANGE = "exchange";
    public static final String ROUTING_KEY = "registration_confirmation_email_routing_key";

    @Bean
    public Queue queue2() { return new Queue(QUEUE); }

    @Bean
    public Binding binding2(TopicExchange exchange)
    {
        return BindingBuilder.bind(queue2()).to(exchange).with(ROUTING_KEY);
    }
}
