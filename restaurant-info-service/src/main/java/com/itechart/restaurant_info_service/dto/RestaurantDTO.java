package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RestaurantDTO {
    @NotNull(message = "Manager id is required")
    private Long restaurantId;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 100, message = "Name string length limits exceeded")
    private String name;

    @Size(max = 50, message = "Phone number string length limits exceeded")
    private String phoneNumber;

    @Size(max = 200, message = "Description string length limits exceeded")
    private String description;

    @Size(max = 200, message = "Restaurant address string length limits exceeded")
    private String restaurantAddress;

    private Double latitude;

    private Double longitude;

    @NotNull(message = "Working time is required")
    private Set<WorkingTimeDTO> workingTime;

    @NotNull(message = "Restaurant types are required")
    private Set<RestaurantTypeDTO> restaurantTypes;
}
