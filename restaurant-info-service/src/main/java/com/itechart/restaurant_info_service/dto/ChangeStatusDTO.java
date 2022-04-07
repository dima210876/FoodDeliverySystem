package com.itechart.restaurant_info_service.dto;

import com.itechart.restaurant_info_service.model.RestaurantStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
public class ChangeStatusDTO {
    @NotNull(message = "Order id is required")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Restaurant status in restaurant is required")
    private RestaurantStatus restaurantStatus;
}
