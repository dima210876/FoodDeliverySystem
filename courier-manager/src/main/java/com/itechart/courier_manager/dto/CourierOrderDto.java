package com.itechart.courier_manager.dto;

import com.itechart.courier_manager.model.Courier;
import lombok.Data;

@Data
public class CourierOrderDto {
    private Courier courier;
    private Long orderId;
    private String deliveryStatus;
    private String deliveryMethod;
}