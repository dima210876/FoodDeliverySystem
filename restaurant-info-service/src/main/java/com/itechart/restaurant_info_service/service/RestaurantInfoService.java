package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.RestaurantDTO;
import com.itechart.restaurant_info_service.dto.RestaurantTypeDTO;
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
import java.util.List;
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
            Set<WorkingTime> current = restaurant.getWorkingTime();
            workingTimeRepository.deleteAll(current);
            workingTimeRepository.findAll();

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
            //  throw new EditRestaurantException("Couldn't edit working hours");
            throw new EditRestaurantException(ex.getMessage());
        }

        return restaurant;
    }

    @Transactional
    public Restaurant editRestaurantTypes(RestaurantDTO restaurantDTO, Restaurant restaurant) throws EditRestaurantException {
        try {
            Set<RestaurantType> current = restaurant.getRestaurantTypes();
            restaurantTypeRepository.deleteAll(current);

            Set<RestaurantType> restaurantTypes = new HashSet<>();

            for (RestaurantTypeDTO restaurantTypeDTO : restaurantDTO.getRestaurantTypes()) {
                restaurantTypes.add(RestaurantType.builder()
                        .restaurantType(restaurantTypeDTO.getRestaurantType())
                        .build());
            }

            restaurantTypeRepository.saveAll(restaurantTypes);
            restaurant.setRestaurantTypes(restaurantTypes);
        } catch (Throwable ex) {
            throw new EditRestaurantException(ex.getMessage());
            // throw new EditRestaurantException("Couldn't edit restaurant types");
        }

        return restaurant;
    }

    @Transactional
    public Restaurant createDefaultWorkingTime(Restaurant restaurant) {
        final int daysOfWeek = 7;
        Set<WorkingTime> workingTimeSet = new HashSet<>();

        for (int current = 1; current <= daysOfWeek; current++) {
            workingTimeSet.add(WorkingTime.builder()
                    .restaurant(restaurant)
                    .dayOfWeek(current)
                    .openingTime("")
                    .closingTime("")
                    .build());
        }

        workingTimeRepository.saveAll(workingTimeSet);
        restaurant.setWorkingTime(workingTimeSet);

        return restaurant;
    }
}
