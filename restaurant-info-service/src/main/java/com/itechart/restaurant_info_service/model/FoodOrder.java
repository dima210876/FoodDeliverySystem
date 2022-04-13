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
import java.util.List;
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
   // @Enumerated(EnumType.STRING)
    @NotNull(message = "Restaurant status is required")
   // private RestaurantStatus restaurantStatus;
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
}
