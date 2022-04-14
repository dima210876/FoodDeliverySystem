package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.ItemInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemInOrderRepository extends JpaRepository<ItemInOrder, Long> {
    Optional<ItemInOrder> findByOrder_Id(Long orderId);
}
