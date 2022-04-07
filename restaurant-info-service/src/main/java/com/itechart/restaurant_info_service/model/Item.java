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
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 100, message = "Name string length limits exceeded")
    private String name;

    @Column(name = "description")
    @Size(min = 2, max = 200, message = "Description string length limits exceeded")
    private String description;

    @Column(name = "price")
    @NotNull(message = "Price is required")
    private Double price;

    @Column(name = "available")
    @NotNull(message = "Available field is required")
    private Boolean available;

    @Column(name = "item_type")
    @NotNull(message = "Item type is required")
    @NotBlank(message = "Item type can't be empty")
    @Size(min = 2, max = 100, message = "Item type string length limits exceeded")
    private String itemType;

    @Column(name = "feature")
    @Size(min = 2, max = 100, message = "Feature string length limits exceeded")
    private String feature;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull(message = "Restaurant is required")
    @JsonBackReference
    private Restaurant restaurant;

    @OneToMany(mappedBy = "item")
    @JsonManagedReference
    private List<IngredientInItem> ingredientsInItems;

    @OneToMany(mappedBy = "item")
    @JsonBackReference
    private List<ItemInOrder> itemsInOrders;
}
