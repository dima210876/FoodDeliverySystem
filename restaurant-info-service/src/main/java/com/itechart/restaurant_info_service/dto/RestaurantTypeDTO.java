package com.itechart.restaurant_info_service.dto;


import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class RestaurantTypeDTO {
    private Long id;

    @Size(max = 200, message = "Restaurant type string length limits exceeded")
    private String restaurantType;
}
