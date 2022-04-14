package com.itechart.food_delivery.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantAddressesDTO {
    private List<String> addresses;
}
