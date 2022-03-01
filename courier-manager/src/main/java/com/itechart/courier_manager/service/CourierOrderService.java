package com.itechart.courier_manager.service;


import com.itechart.courier_manager.repository.CourierOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourierOrderService {
    private final CourierOrderRepository courierOrderRepository;
}
