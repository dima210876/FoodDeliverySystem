package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ItemDTO {
    private String name;
    private String description;
    private double price;
    private boolean available;
    private String itemType;
    private String feature;
    private Set<IngredientDTO> ingredients;
}
