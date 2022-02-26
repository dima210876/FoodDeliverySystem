package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RestaurantDTO {
    private Long managerId;
    private String name;
    private String phoneNumber;
    private String description;
    private String restaurantAddress;
    private double latitude;
    private double longitude;
    private Set<WorkingTimeDTO> workingTime;
    private Set<String> restaurantTypes;
    private Set<ItemDTO> items;
}
