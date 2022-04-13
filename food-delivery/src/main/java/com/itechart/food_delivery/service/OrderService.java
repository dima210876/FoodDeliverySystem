package com.itechart.food_delivery.service;

import com.itechart.food_delivery.dto.CustomerDTO;
import com.itechart.food_delivery.dto.OrderDto;
import com.itechart.food_delivery.exception.CustomerRegistrationException;
import com.itechart.food_delivery.exception.GettingAllOrdersException;
import com.itechart.food_delivery.model.Customer;
import com.itechart.food_delivery.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Service
@Validated
@AllArgsConstructor
public class OrderService {
    @LoadBalanced
    private final RestTemplate restTemplate;

    @Transactional
    public Order postNewOrder(@Valid OrderDto orderDTO) {
        return new Order();
    }

    @Transactional
    public Order getAllOrders() throws GettingAllOrdersException {
        return new Order();
    }
}
