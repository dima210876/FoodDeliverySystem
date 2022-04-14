package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemInOrderDTO {
    @NotNull(message = "Item id is required")
    private Long itemId;

    @NotNull(message = "Amount is required")
    private int amount;
}
