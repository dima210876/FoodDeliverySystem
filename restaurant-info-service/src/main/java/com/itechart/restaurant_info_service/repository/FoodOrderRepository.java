package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    List<FoodOrder> findByRestaurantId(Long restaurantId);
}
