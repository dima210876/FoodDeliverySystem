package com.itechart.restaurant_info_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotBlank(message = "Order status can't be empty")
    @Size(min = 1, max = 20, message = "Order status string length limits exceeded")
    private String orderStatus;
}
