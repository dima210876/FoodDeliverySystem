package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
