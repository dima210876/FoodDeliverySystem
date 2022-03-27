package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.ItemDTO;
import com.itechart.restaurant_info_service.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Validated
public class ItemService {
    private final ItemRepository itemRepository;

    public Page<ItemDTO> getItems(String itemType, Pageable pageable){
        return itemRepository.findByItemType(itemType, pageable);
    }
}
