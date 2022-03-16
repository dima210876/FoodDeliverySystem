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
        //TODO set default values: address, latitude, longitude
        Restaurant restaurant = Restaurant.builder()
                .manager(manager)
                .name(restaurantName)
                .build();

        restaurantRepository.save(restaurant);
    }
}