package com.itechart.courier_manager.dto;

import lombok.Data;

@Data
public class CourierOrderDto {
    private Long id;
    private Long courierId;
    private Long orderId;
    private String deliveryStatus;
    private String deliveryMethod;
}