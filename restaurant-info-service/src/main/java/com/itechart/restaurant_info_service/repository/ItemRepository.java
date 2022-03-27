package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.dto.ItemDTO;
import com.itechart.restaurant_info_service.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    @Query(value = "select new com.itechart.restaurant_info_service.dto.ItemDTO(i.id, i.name, i.description, i.price, i.available, i.itemType, i.feature, r.name)\n" +
            "from  Item i JOIN Restaurant r on i.restaurant.id = r.id\n" +
            "where i.itemType = ?1")
    Page<ItemDTO> findByItemType(String itemType, Pageable pageable);

}
