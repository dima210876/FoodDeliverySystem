package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class FoodOrderDTO {
    private Long restaurantId;
    private Set<ItemInOrderDTO> items;
}
