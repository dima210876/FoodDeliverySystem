package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.IngredientDTO;
import com.itechart.restaurant_info_service.dto.ItemDTO;
import com.itechart.restaurant_info_service.dto.RestaurantDTO;
import com.itechart.restaurant_info_service.dto.WorkingTimeDTO;
import com.itechart.restaurant_info_service.model.*;
import com.itechart.restaurant_info_service.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final WorkingTimeRepository workingTimeRepository;
    private final RestaurantTypeRepository restaurantTypeRepository;
    private final ItemRepository itemRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientInItemRepository ingredientInItemRepository;

    public void addRestaurant(RestaurantDTO restaurantDTO){
        //TODO validation
        //TODO manager must exist

        Restaurant restaurant = Restaurant.builder()
                .manager(Manager.builder().user_id(restaurantDTO.getManagerId()).build())
                .name(restaurantDTO.getName())
                .phoneNumber(restaurantDTO.getPhoneNumber())
                .description(restaurantDTO.getDescription())
                .restaurantAddress(restaurantDTO.getRestaurantAddress())
                .latitude(restaurantDTO.getLatitude())
                .longitude(restaurantDTO.getLongitude())
                .build();

        restaurantRepository.save(restaurant);

        Set<WorkingTime> workingTimeSet = new HashSet<>();

        for(WorkingTimeDTO workingTimeDTO: restaurantDTO.getWorkingTime()){
            workingTimeSet.add(WorkingTime.builder()
                    .openingTime(workingTimeDTO.getOpeningTime())
                    .closingTime(workingTimeDTO.getClosingTime())
                    .dayOfWeek(workingTimeDTO.getDayOfWeek())
                    .restaurant(restaurant)
                    .build());
        }

        workingTimeRepository.saveAll(workingTimeSet);

        Set<RestaurantType> restaurantTypes = new HashSet<>();

        for(String restaurantType: restaurantDTO.getRestaurantTypes()){
            restaurantTypes.add(RestaurantType.builder()
                            .restaurantType(restaurantType)
                             .build());
        }
        //TODO insert only if type doesn't exist
        restaurantTypeRepository.saveAll(restaurantTypes);

        restaurant.setRestaurantTypes(restaurantTypes);

        restaurantRepository.save(restaurant);

        for(ItemDTO itemDTO: restaurantDTO.getItems()){
            Item item = Item.builder()
                    .name(itemDTO.getName())
                    .description(itemDTO.getDescription())
                    .price(itemDTO.getPrice())
                    .available(itemDTO.isAvailable())
                    .itemType(itemDTO.getItemType())
                    .feature(itemDTO.getFeature())
                    .restaurant(restaurant)
                    .build();
            itemRepository.save(item);

            for(IngredientDTO ingredientDTO: itemDTO.getIngredients()){
                Ingredient ingredient = Ingredient.builder()
                        .name(ingredientDTO.getName())
                        .build();
                //TODO check if ingredient exists
                ingredientRepository.save(ingredient);

                IngredientInItem ingredientInItem = IngredientInItem.builder()
                        .amount(ingredientDTO.getAmount())
                        .unit(ingredientDTO.getUnit())
                        .item(item)
                        .ingredient(ingredient)
                        .build();

                ingredientInItemRepository.save(ingredientInItem);
            }
        }
    }
}