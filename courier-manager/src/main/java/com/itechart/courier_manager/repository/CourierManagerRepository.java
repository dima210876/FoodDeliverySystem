package com.itechart.courier_manager.repository;

import com.itechart.courier_manager.model.CourierManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierManagerRepository extends JpaRepository<CourierManager, Long> {
}
