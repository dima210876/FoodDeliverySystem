package com.itechart.courier_manager.repository;

import com.itechart.courier_manager.model.CourierOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourierOrderRepository extends JpaRepository<CourierOrder, Long> {
    List<CourierOrder> findAllByCourier_UserId(Long courierId);
    Optional<CourierOrder> findByOrderId(Long orderId);
}
