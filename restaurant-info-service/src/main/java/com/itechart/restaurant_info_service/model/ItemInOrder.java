package com.itechart.restaurant_info_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders_items")
public class ItemInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id")
    @NotNull(message = "Item is required")
    @JsonManagedReference
    private Item item;

    @Column(name = "amount")
    @NotNull(message = "Amount is required")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name="order_id")
    @NotNull(message = "Order is required")
    @JsonBackReference
    private FoodOrder order;
}
