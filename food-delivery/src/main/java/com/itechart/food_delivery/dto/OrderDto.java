package com.itechart.food_delivery.dto;

import com.itechart.food_delivery.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
//    @Min(value = 1L, message = "Order ID min limit exceeded")
//    @Max(value = Long.MAX_VALUE, message = "Order ID max limit exceeded")
    private Long id;

    private Long courierId;

//    @NotNull(message = "Customer is required")
//    private Customer customer;

    @NotNull(message = "Customer ID is required")
    @Min(value = 1L, message = "Customer ID min limit exceeded")
    @Max(value = Long.MAX_VALUE, message = "Customer ID max limit exceeded")
    private Long customerId;

    @NotNull(message = "Order status is required")
    @NotBlank(message = "Order status can't be empty")
    @Size(min = 1, max = 20, message = "Order status string length limits exceeded")
    private String orderStatus;

    @NotNull(message = "Order address is required")
    @NotBlank(message = "Order address can't be empty")
    @Size(min = 1, max = 200, message = "Order address string length limits exceeded")
    private String orderAddress;

    @NotNull(message = "Order price is required")
    private Double orderPrice;

    @NotNull(message = "Shipping price is required")
    private Double shippingPrice;

    @NotNull(message = "Discount is required")
    @Max(value = 100L, message = "Discount max limit exceeded")
    private Integer discount;

    @NotNull(message = "Creation time is required")
    private LocalDateTime creationTime;

    private LocalDateTime deliveryTime;

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;

    @NotNull(message = "Items are required")
    private Set<ItemDTO> items;
}
