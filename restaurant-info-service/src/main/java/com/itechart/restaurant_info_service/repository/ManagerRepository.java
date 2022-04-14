package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByUserId(Long userId);

    @Query(value = "select r.id\n" +
            "from managers m join restaurants r on r.manager_id = m.user_id where m.email = ?1", nativeQuery = true)
    Long findRestaurantIdByManagerEmail(String email);
}
