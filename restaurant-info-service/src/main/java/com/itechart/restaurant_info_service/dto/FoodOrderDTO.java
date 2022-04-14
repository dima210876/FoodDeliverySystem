package com.itechart.restaurant_info_service.dto;

import com.itechart.restaurant_info_service.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrderDTO {
    private Long id;

    @NotNull(message = "Item ID is required")
    @Min(value = 1L, message = "Item ID min limit exceeded")
    @Max(value = Long.MAX_VALUE, message = "Item ID max limit exceeded")
    private Long itemId;

    private Integer amount;

    @NotNull(message = "Order status is required")
    // @Enumerated(EnumType.STRING)
    @NotNull(message = "Restaurant status is required")
    private String orderStatus;
}
