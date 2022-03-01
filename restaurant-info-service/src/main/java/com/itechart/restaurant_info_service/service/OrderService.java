package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.FoodOrderDTO;
import com.itechart.restaurant_info_service.dto.ItemInOrderDTO;
import com.itechart.restaurant_info_service.model.*;
import com.itechart.restaurant_info_service.repository.FoodOrderRepository;
import com.itechart.restaurant_info_service.repository.ItemInOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderService {
    private final FoodOrderRepository foodOrderRepository;
    private final ItemInOrderRepository itemInOrderRepository;

    public void addOrder(FoodOrderDTO foodOrderDTO) {
        //TODO do we need to check if the item is in restaurant's menu
        //TODO check if item is available
        //TODO amount validation

        FoodOrder foodOrder = FoodOrder.builder()
                .restaurant(Restaurant.builder().id(foodOrderDTO.getRestaurantId()).build())
                .restaurantStatus(RestaurantStatus.VERIFICATION)
                .build();

        foodOrderRepository.save(foodOrder);

        Set<ItemInOrder> itemsInOrder = new HashSet<>();

        for (ItemInOrderDTO itemInOrderDTO : foodOrderDTO.getItems()) {
            itemsInOrder.add(ItemInOrder.builder()
                    .item(Item.builder().id(itemInOrderDTO.getItemId()).build())
                    .order(foodOrder)
                    .amount(itemInOrderDTO.getAmount())
                    .build());
        }

        itemInOrderRepository.saveAll(itemsInOrder);
    }
}
