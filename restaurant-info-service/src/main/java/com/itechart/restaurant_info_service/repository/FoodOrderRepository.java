package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.FoodOrder;
import com.itechart.restaurant_info_service.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    List<FoodOrder> findByRestaurantId(Long restaurantId);
}
