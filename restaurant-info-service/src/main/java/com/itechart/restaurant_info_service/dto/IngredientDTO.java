package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class IngredientDTO {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 100, message = "Name string length limits exceeded")
    private String name;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotNull(message = "Unit is required")
    @NotBlank(message = "Unit can't be empty")
    @Size(min = 2, max = 20, message = "Unit string length limits exceeded")
    private String unit;
}
