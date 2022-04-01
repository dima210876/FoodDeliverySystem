package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.dto.ManagerDTO;
import com.itechart.restaurant_info_service.model.Manager;
import com.itechart.restaurant_info_service.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    /*@Query("select new com.itechart.restaurant_info_service.model.Restaurant(m.restaurant)\n" +
            "from  Manager m where m.email = ?1")
    Restaurant findRestaurantByManagerEmail(String email);*/
}
