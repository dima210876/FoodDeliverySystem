package com.itechart.restaurant_info_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 100, message = "Name string length limits exceeded")
    private String name;

    @Size(min = 0, max = 50, message = "Phone number string length limits exceeded")
    private String phoneNumber;

    @Size(max = 200, message = "Description string length limits exceeded")
    private String description;

    @Size(max = 200, message = "Restaurant address string length limits exceeded")
    private String restaurantAddress;

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;

    @OneToOne
    @JoinColumn(name = "manager_id")
    @JsonBackReference
    private Manager manager;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private Set<WorkingTime> workingTime;

    @ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "restaurants_restaurant_types",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_type_id")
    )
    @JsonManagedReference
    private Set<RestaurantType> restaurantTypes;

    @OneToMany(mappedBy = "restaurant")
    @JsonManagedReference
    private Set<Item> items;

    @OneToMany
    @JoinColumn(name = "restaurantId")
    @JsonManagedReference
    private Set<FoodOrder> foodOrders;

    public Restaurant(Restaurant restaurant){

    }
}
