package com.itechart.food_delivery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "courier_id")
    private Long courierId;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(
                    name = "FK_customers_id"
            )
    )

    @NotNull(message = "Customer is required")
    @JsonBackReference
    private Customer customer;

//    @Column(name = "customer_id")
//    @NotNull(message = "Customer ID is required")
//    private Long customerId;

    @Column(name = "order_status")
    @NotNull(message = "Order status is required")
    @NotBlank(message = "Order status can't be empty")
    @Size(min = 1, max = 20, message = "Order status string length limits exceeded")
    private String orderStatus;

    @Column(name = "order_address")
    @NotNull(message = "Order address is required")
    @NotBlank(message = "Order address can't be empty")
    @Size(min = 1, max = 200, message = "Order address string length limits exceeded")
    private String orderAddress;

    @Column(name = "order_price")
    @NotNull(message = "Order price is required")
    private Double orderPrice;

    @Column(name = "shipping_price")
    @NotNull(message = "Shipping price is required")
    private Double shippingPrice;

    @Column(name = "discount")
    @NotNull(message = "Discount is required")
    @Max(value = 100L, message = "Discount max limit exceeded")
    private Integer discount;

    @Column(name = "creation_time")
    @NotNull(message = "Creation time is required")
    private LocalDateTime creationTime;

    @Column(name = "delivery_time")
    private LocalDateTime deliveryTime;

    @Column(name = "latitude")
    @NotNull(message = "Latitude is required")
    private Double latitude;

    @Column(name = "longitude")
    @NotNull(message = "Longitude is required")
    private Double longitude;
}
