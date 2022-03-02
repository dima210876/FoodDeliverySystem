package com.itechart.courier_manager.service;

import com.itechart.courier_manager.dto.CourierOrderDto;
import com.itechart.courier_manager.model.CourierOrder;
import com.itechart.courier_manager.repository.CourierOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourierOrderService {
    private final CourierOrderRepository courierOrderRepository;

    public void addCourierOrder(CourierOrderDto courierOrderDto) {
        CourierOrder courierOrder = CourierOrder.builder()
                .courier(courierOrderDto.getCourier())
                .orderId(courierOrderDto.getOrderId())
                .deliveryMethod(courierOrderDto.getDeliveryMethod())
                .deliveryStatus(courierOrderDto.getDeliveryStatus())
                .build();
        courierOrderRepository.save(courierOrder);
    }
}
