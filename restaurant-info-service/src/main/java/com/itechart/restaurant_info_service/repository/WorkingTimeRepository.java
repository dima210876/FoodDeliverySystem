package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.Restaurant;
import com.itechart.restaurant_info_service.model.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface WorkingTimeRepository extends JpaRepository<WorkingTime, Long> {
}
