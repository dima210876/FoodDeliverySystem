package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.IngredientInItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IngredientInItemRepository extends JpaRepository<IngredientInItem, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into items_ingredients  (item_id, ingredient_id, amount, unit)" +
            "values (?1, ?2, ?3, ?4)", nativeQuery = true)
    void saveIngredients(Long itemId, Long ingredientId, Double amount, String unit);
}
