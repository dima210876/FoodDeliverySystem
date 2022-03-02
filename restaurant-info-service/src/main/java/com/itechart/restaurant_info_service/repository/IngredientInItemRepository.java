package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.IngredientInItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientInItemRepository extends JpaRepository<IngredientInItem, Long> {
}
