package com.itechart.restaurant_info_service.repository;

import com.itechart.restaurant_info_service.dto.ItemDTO;
import com.itechart.restaurant_info_service.dto.NewItemDTO;
import com.itechart.restaurant_info_service.model.Item;
import com.itechart.restaurant_info_service.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    @Query("select new com.itechart.restaurant_info_service.dto.ItemDTO(i.id, i.name, i.description, i.price, i.available, i.itemType, i.feature, r.name, r.id)\n" +
            "from  Item i JOIN Restaurant r on i.restaurant.id = r.id\n" +
            "where i.itemType = ?1  and i.name like %?2% and i.price between ?3 and ?4 and r.name like %?5%")
    Page<ItemDTO> findItemsByItemType(String itemType, String filterName, double filterMinPrice, double filterMaxPrice, String filterRestaurant, Pageable pageable);

}
