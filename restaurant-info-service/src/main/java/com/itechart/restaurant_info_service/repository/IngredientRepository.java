package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
