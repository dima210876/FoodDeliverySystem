package com.itechart.food_delivery.service;

import com.itechart.food_delivery.dto.OrderDto;
import com.itechart.food_delivery.exception.OrderNotFoundException;
import com.itechart.food_delivery.exception.OrderStatusChangeException;
import com.itechart.food_delivery.model.Order;
import com.itechart.food_delivery.model.OrderStatus;
import com.itechart.food_delivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodDeliveryService
{
    private final OrderRepository orderRepository;

    public OrderDto getOrder(Long orderId) throws OrderNotFoundException
    {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new OrderNotFoundException("Order was not found.");
        });
        return convertToDto(order);
    }

    public String getOrderStatusForCustomer(Long id) throws OrderNotFoundException
    {
        Order order = orderRepository.findById(id).orElseThrow(() -> {
            throw new OrderNotFoundException("Order was not found.");
        });
        return order.getOrderStatus();
    }

    public void changeOrderStatus(Long orderId, String newStatus)
            throws OrderNotFoundException, OrderStatusChangeException, IllegalArgumentException
    {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new OrderNotFoundException("Order was not found.");
        });
        OrderStatus currentStatus = OrderStatus.valueOf(order.getOrderStatus().toUpperCase());
        OrderStatus potentialStatus = OrderStatus.valueOf(newStatus.toUpperCase());
        switch (currentStatus)
        {
            case NOT_PAID:
                if (potentialStatus != OrderStatus.PAID)
                {
                    throw new OrderStatusChangeException("Wrong order status.");
                }
                break;
            case PAID:
                if (potentialStatus != OrderStatus.VERIFICATION)
                {
                    throw new OrderStatusChangeException("Wrong order status.");
                }
                break;
            case VERIFICATION:
                if (potentialStatus != OrderStatus.COOKING)
                {
                    throw new OrderStatusChangeException("Wrong order status.");
                }
                break;
            case COOKING:
                if (potentialStatus != OrderStatus.READY)
                {
                    throw new OrderStatusChangeException("Wrong order status.");
                }
                break;
            case READY:
                if (potentialStatus != OrderStatus.DELIVERING)
                {
                    throw new OrderStatusChangeException("Wrong order status.");
                }
                break;
            case DELIVERING:
                if (potentialStatus != OrderStatus.DELIVERED)
                {
                    throw new OrderStatusChangeException("Wrong order status.");
                }
                break;
            case DELIVERED:
                throw new OrderStatusChangeException("The order has reached the final status.");
        }
        order.setOrderStatus(potentialStatus.getStatus());
        orderRepository.save(order);
    }

    private OrderDto convertToDto(Order order)
    {
        return OrderDto.builder()
                .id(order.getId())
                .courierId(order.getCourierId())
                .customerId(order.getCustomerId())
                .orderStatus(order.getOrderStatus())
                .orderAddress(order.getOrderAddress())
                .orderPrice(order.getOrderPrice())
                .shippingPrice(order.getShippingPrice())
                .discount(order.getDiscount())
                .creationTime(order.getCreationTime())
                .deliveryTime(order.getDeliveryTime())
                .latitude(order.getLatitude())
                .longitude(order.getLongitude())
                .build();
    }
}
