package com.itechart.food_delivery.repository;

import com.itechart.food_delivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodDeliveryRepository extends JpaRepository<Order, Long> {

}
