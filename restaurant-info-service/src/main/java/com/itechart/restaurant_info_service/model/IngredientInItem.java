package com.itechart.restaurant_info_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "items_ingredients")
public class IngredientInItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @NotNull(message = "Item is required")
    @JsonBackReference
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @NotNull(message = "Ingredient is required")
    @JsonBackReference
    private Ingredient ingredient;

    @Column(name = "amount")
    @NotNull(message = "Amount is required")
    private Double amount;

    @Column(name = "unit")
    @NotNull(message = "Unit is required")
    @NotBlank(message = "Unit can't be empty")
    @Size(min = 2, max = 20, message = "Unit string length limits exceeded")
    private String unit;
}
