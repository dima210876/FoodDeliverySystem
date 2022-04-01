package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.IngredientDTO;
import com.itechart.restaurant_info_service.dto.ItemDTO;
import com.itechart.restaurant_info_service.dto.ManagerDTO;
import com.itechart.restaurant_info_service.dto.NewItemDTO;
import com.itechart.restaurant_info_service.model.Manager;
import com.itechart.restaurant_info_service.model.Restaurant;
import com.itechart.restaurant_info_service.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
@AllArgsConstructor
@Validated
public class ItemService {

    private final ManagerService managerService;

    private final ItemRepository itemRepository;

    public Page<ItemDTO> getItems(String itemType, String filterName, double filterMinPrice, double filterMaxPrice, String filterRestaurant, Pageable pageable){
        return itemRepository.findItemsByItemType(itemType, filterName, filterMinPrice, filterMaxPrice, filterRestaurant, pageable);
    }

    /*public void addItem(NewItemDTO newItemDTO, Set<IngredientDTO> ingredients, ManagerDTO managerDTO, MultipartFile image){
        Restaurant restaurant = managerService.findRestaurantByManagerEmail(managerDTO);
    }*/



}
