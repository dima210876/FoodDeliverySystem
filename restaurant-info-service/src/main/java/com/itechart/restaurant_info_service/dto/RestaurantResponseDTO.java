package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RestaurantResponseDTO {
    private Long restaurantId;
    private String name;
    private String phoneNumber;
    private String description;
    private String restaurantAddress;
    private Double latitude;
    private Double longitude;
    private Set<WorkingTimeDTO> workingTime;
    private Set<RestaurantTypesDTO> restaurantTypes;
}
