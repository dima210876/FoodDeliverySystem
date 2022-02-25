package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
