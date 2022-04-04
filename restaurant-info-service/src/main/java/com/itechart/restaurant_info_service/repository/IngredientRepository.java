package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.dto.IngredientDTO;
import com.itechart.restaurant_info_service.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into ingredients (name) values (?1)", nativeQuery = true)
    void saveIngredient(String name);

    @Transactional
    @Query(value = "SELECT max(id) FROM Ingredient")
    Long findMaxId();
}
