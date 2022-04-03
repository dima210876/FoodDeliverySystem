package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.IngredientDTO;
import com.itechart.restaurant_info_service.model.Ingredient;
import com.itechart.restaurant_info_service.repository.IngredientInItemRepository;
import com.itechart.restaurant_info_service.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Validated
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientInItemRepository ingredientInItemRepository;

    public void saveIngredients(Set<IngredientDTO> ingredients, Long itemId){
        for (var ingredient : ingredients) {
            Optional<Ingredient> ingredientFromDB = ingredientRepository.findByName(ingredient.getName());
            if (ingredientFromDB.isPresent())
                ingredientInItemRepository.saveIngredients(itemId, ingredientFromDB.get().getId(),
                        ingredient.getAmount(), ingredient.getUnit());
            else{
                ingredientRepository.saveIngredient(ingredient.getName());
                long ingredientId = ingredientRepository.findMaxId();
                ingredientInItemRepository.saveIngredients(itemId, ingredientId,
                        ingredient.getAmount(), ingredient.getUnit());
            }

        }
    }

}
