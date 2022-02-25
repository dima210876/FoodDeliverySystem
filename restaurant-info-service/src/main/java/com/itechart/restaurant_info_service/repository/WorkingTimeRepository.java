package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingTimeRepository extends JpaRepository<WorkingTime, Long> {
}
