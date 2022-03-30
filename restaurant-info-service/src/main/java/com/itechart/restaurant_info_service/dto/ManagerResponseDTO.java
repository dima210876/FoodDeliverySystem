package com.itechart.restaurant_info_service.dto;

import lombok.Data;

@Data
public class ManagerResponseDTO {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private RestaurantResponseDTO restaurant;
}
