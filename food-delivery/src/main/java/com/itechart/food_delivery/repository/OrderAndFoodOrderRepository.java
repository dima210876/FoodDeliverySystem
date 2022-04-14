package com.itechart.food_delivery.repository;

import com.itechart.food_delivery.model.OrderAndFoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderAndFoodOrderRepository extends JpaRepository<OrderAndFoodOrder, Long> {
    List<OrderAndFoodOrder> findAllByOrderId(Long orderId);

    List<OrderAndFoodOrder> findAllByFoodOrderId(Long foodOrderId);

    Optional<OrderAndFoodOrder> findByFoodOrderId(Long foodOrderId);
}
