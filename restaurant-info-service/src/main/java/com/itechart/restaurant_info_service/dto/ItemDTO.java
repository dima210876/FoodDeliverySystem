package com.itechart.restaurant_info_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    @Id
    private Long id;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 100, message = "Name string length limits exceeded")
    private String name;

    @Size(min = 2, max = 200, message = "Description string length limits exceeded")
    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Available field is required")
    private Boolean available;

    @NotNull(message = "Item type is required")
    @NotBlank(message = "Item type can't be empty")
    @Size(min = 2, max = 100, message = "Item type string length limits exceeded")
    private String itemType;

    @Size(min = 2, max = 100, message = "Feature string length limits exceeded")
    private String feature;

    @NotNull(message = "Restaurant are required")
    private String restaurant;

    private byte[] image;

    public ItemDTO(Long id, String name, String description, Double price, Boolean available, String itemType, String feature, String restaurant) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.itemType = itemType;
        this.feature = feature;
        this.restaurant = restaurant;
    }
}
