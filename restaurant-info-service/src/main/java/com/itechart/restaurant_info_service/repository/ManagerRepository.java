package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByUserId(Long userId);
}
