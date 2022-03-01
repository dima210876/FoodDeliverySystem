package com.itechart.courier_manager.repository;

import com.itechart.courier_manager.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {
}
