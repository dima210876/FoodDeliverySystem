package com.itechart.courier_manager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "courier_orders")
public class CourierOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "courier_id",
            foreignKey = @ForeignKey(
                    name = "FK_couriers_id"
            )
    )
    @NotNull(message = "Courier is required")
    @JsonBackReference
    private Courier courier;

    @NotNull(message = "Order id is required")
    private Long orderId;

    @NotNull(message = "Delivery status is required")
    @NotBlank(message = "Delivery status can't be empty")
    @Size(max = 30, message = "Delivery status string length limits exceeded")
    private String deliveryStatus;

    @NotNull(message = "Delivery method is required")
    @NotBlank(message = "Delivery method can't be empty")
    @Size(max = 30, message = "Delivery method string length limits exceeded")
    private String deliveryMethod;
}