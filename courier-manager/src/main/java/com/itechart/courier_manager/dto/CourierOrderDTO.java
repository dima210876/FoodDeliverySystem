package com.itechart.courier_manager.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourierOrderDTO {
    private Long id;
    private List<String> restaurantAddresses;
    private String deliveryAddress;
    private String orderStatus;
}