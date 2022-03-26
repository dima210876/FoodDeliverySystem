package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    Page<Item> findByItemType(String itemType, Pageable pageable);

}
