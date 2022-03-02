package com.itechart.courier_manager.repository;

import com.itechart.courier_manager.model.CourierOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierOrderRepository extends JpaRepository<CourierOrder, Long> {
}
