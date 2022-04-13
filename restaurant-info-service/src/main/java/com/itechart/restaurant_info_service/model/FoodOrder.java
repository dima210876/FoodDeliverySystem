package com.itechart.restaurant_info_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "food_orders")
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_status")
    @NotNull(message = "Restaurant status is required")
    private String restaurantStatus;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull(message = "Restaurant is required")
    @JsonBackReference
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<ItemInOrder> itemsInOrders;

    @Transient
    private Double orderPrice;

    @Transient
    private Timestamp creationTime;
}
