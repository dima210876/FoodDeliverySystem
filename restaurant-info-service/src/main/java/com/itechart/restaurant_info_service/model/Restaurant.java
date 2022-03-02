package com.itechart.restaurant_info_service.model;

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
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 100, message = "Name string length limits exceeded")
    private String name;

    @Column(name = "phone_Number")
    @Size(min = 2, max = 50, message = "Phone number string length limits exceeded")
    private String phoneNumber;

    @Column(name = "description")
    @Size(min = 2, max = 200, message = "Description string length limits exceeded")
    private String description;


    @Column(name = "restaurant_address")
    @NotNull(message = "Restaurant address is required")
    @NotBlank(message = "Restaurant address can't be empty")
    @Size(min = 2, max = 200, message = "Restaurant address string length limits exceeded")
    private String restaurantAddress;

    @Column(name = "latitude")
    @NotNull(message = "Latitude is required")
    private Double latitude;

    @Column(name = "longitude")
    @NotNull(message = "Longitude is required")
    private Double longitude;

    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "user_id")
    @NotNull(message = "Manager is required")
    @JsonManagedReference
    private Manager manager;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private Set<WorkingTime> workingTime;

    @ManyToMany
    @JoinTable(name = "restaurants_restaurant_types",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_type_id")
    )
    @JsonManagedReference
    private Set<RestaurantType> restaurantTypes;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private Set<Item> items;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private Set<FoodOrder> foodOrders;
}
