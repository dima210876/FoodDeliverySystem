package com.itechart.food_delivery.dto;

import com.itechart.food_delivery.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadyOrderDTO {

    @NotNull(message = "Id is required")
    private Long id;

    @NotNull(message = "Customer is required")
    private Customer customer;

    @NotNull(message = "Order price is required")
    private double orderPrice;

    @NotNull(message = "Order address is required")
    private String orderAddress;

}
