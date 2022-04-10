package com.itechart.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    @NotNull(message = "Id is required")
    private Long id;

    @NotNull(message = "Restaurant id is required")
    private Long restaurantId;

    private int count;
    private double price;
}
