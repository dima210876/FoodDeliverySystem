package com.itechart.restaurant_info_service.dto;

import lombok.Data;

@Data
public class IngredientDTO {
    private String name;
    private double amount;
    private String unit;
}
