package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    List<FoodOrder> findAllByRestaurantId(Long restaurantId);
}
