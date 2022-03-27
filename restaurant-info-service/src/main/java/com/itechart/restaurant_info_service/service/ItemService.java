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
    private static final String EMPTY = "";
    private static final String SIGN_OF_PERCENT = "%";
    private static final String QUOTE = "'";
    private final ItemRepository itemRepository;

    public Page<ItemDTO> getItems(String itemType, String filter, Pageable pageable){
        if(filterEmpty(filter))
            return itemRepository.findItemsByItemType(itemType, pageable);
        return itemRepository.findItemsByFilter(itemType, convertFilter(filter), pageable);
    }

    private boolean filterEmpty(String filter){
        return filter.equals(EMPTY);
    }

    private String convertFilter(String filter){
        return filter;
    }
}
