package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
