package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class FoodOrderDTO {
    @NotNull(message = "Restaurant id is required")
    private Long restaurantId;

    @NotNull(message = "Items are required")
    private Set<ItemInOrderDTO> items;
}
