package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
