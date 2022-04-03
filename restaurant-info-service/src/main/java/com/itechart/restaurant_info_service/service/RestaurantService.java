package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.model.Manager;
import com.itechart.restaurant_info_service.model.Restaurant;
import com.itechart.restaurant_info_service.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Validated
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void createDefaultRestaurant(Manager manager, String restaurantName){
        final String DEFAULT_ADDRESS = "Unknown";

        Restaurant restaurant = Restaurant.builder()
                .manager(manager)
                .name(restaurantName)
                .restaurantAddress(DEFAULT_ADDRESS)
                .latitude(0D)
                .longitude(0D)
                .build();

        try{
            restaurantRepository.save(restaurant);
        } catch (RuntimeException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Restaurant findRestaurantById(Long restaurantId){
        return restaurantRepository.findById(restaurantId).get();
    }


}