package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.RestaurantDTO;
import com.itechart.restaurant_info_service.dto.WorkingTimeDTO;
import com.itechart.restaurant_info_service.exception.EditRestaurantException;
import com.itechart.restaurant_info_service.model.Manager;
import com.itechart.restaurant_info_service.model.Restaurant;
import com.itechart.restaurant_info_service.model.RestaurantType;
import com.itechart.restaurant_info_service.model.WorkingTime;
import com.itechart.restaurant_info_service.repository.RestaurantRepository;
import com.itechart.restaurant_info_service.repository.RestaurantTypeRepository;
import com.itechart.restaurant_info_service.repository.WorkingTimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Validated
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantInfoService restaurantInfoService;

    public void createDefaultRestaurant(Manager manager, String restaurantName) {
        try {
            final String DEFAULT_ADDRESS = "Unknown";

            Restaurant restaurant = Restaurant.builder()
                    .manager(manager)
                    .name(restaurantName)
                    .restaurantAddress(DEFAULT_ADDRESS)
                    .latitude(0D)
                    .longitude(0D)
                    .build();
            restaurantRepository.save(restaurant);

            restaurant = restaurantInfoService.createDefaultWorkingTime(restaurant);

            restaurantRepository.save(restaurant);
        } catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Restaurant editRestaurantInfo(@Valid RestaurantDTO restaurantDTO) throws EditRestaurantException {
        try {
            Optional<Restaurant> optionalRestaurant = restaurantRepository
                    .findById(restaurantDTO.getRestaurantId());

            if (optionalRestaurant.isEmpty()) {
                throw new EditRestaurantException(String.format("Restaurant with id %d doesn't exist", restaurantDTO.getRestaurantId()));
            }

            Restaurant restaurant = optionalRestaurant.get();

            restaurant.setName(restaurantDTO.getName());
            restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
            restaurant.setRestaurantAddress(restaurantDTO.getRestaurantAddress());
            restaurant.setLatitude(restaurantDTO.getLatitude());
            restaurant.setLongitude(restaurantDTO.getLongitude());

            restaurant = restaurantRepository.save(restaurant);

            restaurant = restaurantInfoService.editWorkingTime(restaurantDTO, restaurant);

            restaurant = restaurantInfoService.editRestaurantTypes(restaurantDTO, restaurant);

            return restaurantRepository.save(restaurant);
        } catch (Throwable ex) {
            throw new EditRestaurantException(ex.getMessage());
        }
    }

    public Restaurant findRestaurantById(Long restaurantId){
        return restaurantRepository.findById(restaurantId).get();
    }

}