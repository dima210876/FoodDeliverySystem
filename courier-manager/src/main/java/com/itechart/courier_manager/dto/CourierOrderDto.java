package com.itechart.courier_manager.dto;

import com.itechart.courier_manager.model.Courier;
import lombok.Data;

@Data
public class CourierOrderDto {
    private Long userId;
    private Long orderId;
}