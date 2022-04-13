package com.itechart.restaurant_info_service.dto;

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
