package com.itechart.food_delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_food_orders")
public class OrderAndFoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotNull(message = "Food order ID is required")
    private Long foodOrderId;

    @NotNull(message = "Order status is required")
    @NotBlank(message = "Order status can't be empty")
    @Size(min = 1, max = 20, message = "Order status string length limits exceeded")
    private String foodOrderStatus;

    @NotNull(message = "Creation time is required")
    private LocalDateTime creationTime;

    //@NotNull(message = "Completion time is required")
    private LocalDateTime completionTime;
}
