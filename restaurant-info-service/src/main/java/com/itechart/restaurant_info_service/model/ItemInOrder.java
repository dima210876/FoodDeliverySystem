package com.itechart.restaurant_info_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders_items")
public class ItemInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    @NotNull(message = "Item is required")
    @JsonBackReference
    private Item item;

    @ManyToOne
    @JoinColumn(name="order_id")
    @NotNull(message = "Order is required")
    @JsonBackReference
    private FoodOrder order;

    @Column(name = "amount")
    @NotNull(message = "Amount is required")
    private Integer amount;
}
