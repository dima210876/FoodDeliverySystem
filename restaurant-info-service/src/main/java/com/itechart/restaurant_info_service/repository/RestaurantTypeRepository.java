package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.RestaurantType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTypeRepository extends JpaRepository<RestaurantType, Long> {
}
