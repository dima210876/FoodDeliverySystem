package com.itechart.restaurant_info_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String description;
    private String restaurantAddress;
    private double latitude;
    private double longitude;

    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "user_id")
    private Manager manager;

    @OneToMany(mappedBy = "restaurant")
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "restaurant")
    private Set<WorkingTime> workingTime;

    @ManyToMany
    @JoinTable(name = "restaurants_restaurant_types",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_type_id")
    )
    private Set<RestaurantType> restaurantTypes;

    @OneToMany(mappedBy = "restaurant")
    private Set<Item> items;

    @OneToMany(mappedBy = "restaurant")
    private Set<FoodOrder> foodOrders;
}
