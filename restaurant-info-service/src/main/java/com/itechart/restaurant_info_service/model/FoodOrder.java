package com.itechart.restaurant_info_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
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
    @NotBlank(message = "Restaurant status can't be empty")
    @Size(min = 2, max = 20, message = "Restaurant status string length limits exceeded")
    private RestaurantStatus restaurantStatus;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull(message = "Restaurant is required")
    @JsonBackReference
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private Set<ItemInOrder> itemsInOrders;
}
