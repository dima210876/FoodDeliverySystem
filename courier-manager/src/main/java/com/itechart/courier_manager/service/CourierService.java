package com.itechart.courier_manager.service;

import com.itechart.courier_manager.dto.CourierDto;
import com.itechart.courier_manager.model.Courier;
import com.itechart.courier_manager.repository.CourierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourierService {
    private final CourierRepository courierRepository;

    public void addCourier(CourierDto courierDto) {
        Courier courier = Courier.builder()
                .role(courierDto.getRole())
                .firstName(courierDto.getFirstName())
                .lastName(courierDto.getLastName())
                .phoneNumber(courierDto.getPhoneNumber())
                .organization(courierDto.getOrganization())
                .orders(courierDto.getOrders())
                .build();
        courierRepository.save(courier);
    }
}
