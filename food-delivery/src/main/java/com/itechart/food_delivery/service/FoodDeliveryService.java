package com.itechart.food_delivery.service;

import com.itechart.food_delivery.exception.OrderNotFound;
import com.itechart.food_delivery.model.Order;
import com.itechart.food_delivery.repository.FoodDeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodDeliveryService {
    private final FoodDeliveryRepository foodDeliveryRepository;

    public String getOrderStatusForCustomer(Long id){
        Order order = foodDeliveryRepository.findById(id).orElseThrow(() -> {
            throw new OrderNotFound("Order was not found.");
        });
        return order.getOrderStatus();
    }
}
