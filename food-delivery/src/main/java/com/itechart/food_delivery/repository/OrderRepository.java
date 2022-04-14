package com.itechart.food_delivery.repository;

import com.itechart.food_delivery.model.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByIdAndDeliveryTimeBetween(Long id, LocalDateTime startDate, LocalDateTime endDate);
}
