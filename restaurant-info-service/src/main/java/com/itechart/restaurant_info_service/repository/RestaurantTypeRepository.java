package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.RestaurantType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantTypeRepository extends JpaRepository<RestaurantType, Long> {
    Optional<RestaurantType> findByRestaurantType(String restaurantType);
}
