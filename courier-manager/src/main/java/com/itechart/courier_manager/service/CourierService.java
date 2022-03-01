package com.itechart.courier_manager.service;

import com.itechart.courier_manager.repository.CourierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourierService {
    private final CourierRepository courierRepository;
}
