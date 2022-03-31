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
@Table(name = "restaurant_types")
public class RestaurantType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Restaurant type is required")
    @NotBlank(message = "Restaurant type can't be empty")
    @Size(min = 2, max = 200, message = "Restaurant type string length limits exceeded")
    private String restaurantType;

    @ManyToMany(mappedBy = "restaurantTypes")
    @JsonManagedReference
    private Set<Restaurant> restaurants;
}
