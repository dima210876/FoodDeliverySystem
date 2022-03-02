package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
}
