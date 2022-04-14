package com.itechart.courier_manager.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddressesDTO {
    private List<String> restaurantAddresses;
    private String deliveryAddress;
}
