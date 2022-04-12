package com.itechart.courier_manager.repository;

import com.itechart.courier_manager.model.Courier;
import com.itechart.courier_manager.model.CourierManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierRepository extends JpaRepository<Courier, Long> {
    Optional<Courier> findByUserId(Long userId);
}
