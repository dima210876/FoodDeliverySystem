package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.RestaurantDTO;
import com.itechart.restaurant_info_service.dto.WorkingTimeDTO;
import com.itechart.restaurant_info_service.exception.EditRestaurantException;
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

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Validated
public class RestaurantInfoService {
    private final WorkingTimeRepository workingTimeRepository;
    private final RestaurantTypeRepository restaurantTypeRepository;

    @Transactional
    public Restaurant editWorkingTime(RestaurantDTO restaurantDTO, Restaurant restaurant) throws EditRestaurantException {
        try {
            workingTimeRepository.deleteAll(restaurant.getWorkingTime());

            Set<WorkingTime> workingTimeSet = new HashSet<>();

            for (WorkingTimeDTO workingTimeDTO : restaurantDTO.getWorkingTime()) {
                workingTimeSet.add(WorkingTime.builder()
                        .openingTime(workingTimeDTO.getOpeningTime())
                        .closingTime(workingTimeDTO.getClosingTime())
                        .dayOfWeek(workingTimeDTO.getDayOfWeek())
                        .restaurant(restaurant)
                        .build());
            }

            workingTimeRepository.saveAll(workingTimeSet);
            restaurant.setWorkingTime(workingTimeSet);
        } catch (Throwable ex) {
            throw new EditRestaurantException("Couldn't edit working hours");
        }

        return restaurant;
    }

    @Transactional
    public Restaurant editRestaurantTypes(RestaurantDTO restaurantDTO, Restaurant restaurant) throws EditRestaurantException {
        try {
            Set<RestaurantType> existing = restaurant.getRestaurantTypes();

            boolean key = true;

            for (String restaurantTypeDTO : restaurantDTO.getRestaurantTypes()) {
                for (RestaurantType existingType : existing) {
                    if (restaurantTypeDTO.equals(existingType.getRestaurantType())) {
                        key = false;
                        break;
                    }
                }

                if (key) {
                    RestaurantType restaurantType = RestaurantType.builder()
                            .restaurantType(restaurantTypeDTO)
                            .build();

                    restaurantTypeRepository.save(restaurantType);
                    restaurant.getRestaurantTypes().add(restaurantType);
                } else {
                    key = true;
                }
            }
        } catch (Throwable ex) {
            throw new EditRestaurantException("Couldn't edit restaurant types");
        }

        return restaurant;
    }
}
