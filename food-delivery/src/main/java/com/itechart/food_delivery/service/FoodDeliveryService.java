package com.itechart.food_delivery.service;

import com.itechart.food_delivery.dto.OrderDTO;
import com.itechart.food_delivery.exception.OrderNotFound;
import com.itechart.food_delivery.model.Order;
import com.itechart.food_delivery.repository.FoodDeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public Order placeAnOrder(OrderDTO orderDTO){
        return foodDeliveryRepository.save(convertOrderDtoToOrder(orderDTO));
    }

    private Order convertOrderDtoToOrder(OrderDTO orderDTO){
        Order order = new Order();
        order.setFoodOrderId(orderDTO.getFoodOrderId());
        order.setOrderAddress(orderDTO.getOrderAddress());
        order.setOrderPrice(orderDTO.getOrderPrice());
        order.setShippingPrice(orderDTO.getShippingOrder());
        order.setDiscount(orderDTO.getDiscount());
        order.setCourierId(getCourierId());
        order.setCustomerId(getCustomerId());
        order.setOrderStatus(getOrderStatus());
        order.setCreationTime(getCreationTime());
        order.setDeliveryTime(getDeliveryTime());
        order.setLatitude(getLatitude());
        order.setLongitude(getLongitude());
        return order;
    }

    private Long getCourierId(){
        //TODO: write an implementation of the method getCourierId
    }

    private Long getCustomerId(){
        //TODO: write an implementation of the method getCustomerId
    }

    private LocalDateTime getCreationTime(){
        //TODO: write an implementation of the method getCreationTime
    }

    private LocalDateTime getDeliveryTime(){
        //TODO: write an implementation of the method getDeliveryTime
    }

    private String getOrderStatus(){
        //TODO: write an implementation of the method getOrderStatus
    }

    private double getLatitude(){
        //TODO: write an implementation of the method getLatitude
    }

    private double getLongitude(){
        //TODO: write an implementation of the method getLongitude
    }
}
