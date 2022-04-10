package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.FoodOrderDTO;
import com.itechart.restaurant_info_service.dto.ItemInOrderDTO;
import com.itechart.restaurant_info_service.exception.ItemNotFoundException;
import com.itechart.restaurant_info_service.model.*;
import com.itechart.restaurant_info_service.repository.FoodOrderRepository;
import com.itechart.restaurant_info_service.repository.ItemInOrderRepository;
import com.itechart.restaurant_info_service.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Validated
public class OrderService {
    private final FoodOrderRepository foodOrderRepository;
    private final ItemInOrderRepository itemInOrderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public FoodOrderDTO addOrder(@Valid FoodOrderDTO foodOrderDTO) throws ItemNotFoundException {
        Optional<Item> optionalItem = itemRepository.findById(foodOrderDTO.getItemId());

        if (optionalItem.isEmpty()) {
            throw new ItemNotFoundException(String.format("Item with id %d not found", foodOrderDTO.getItemId()));
        }

        Item item = optionalItem.get();

        FoodOrder foodOrder = foodOrderRepository.save(FoodOrder.builder()
                .restaurantId(item.getRestaurant().getId())
                .restaurantStatus(foodOrderDTO.getOrderStatus())
                .build());

        itemInOrderRepository.save(ItemInOrder.builder()
                .item(item)
                .order(foodOrder)
                .amount(foodOrderDTO.getAmount())
                .build());

        return FoodOrderDTO.builder()
                .id(foodOrder.getId())
                .itemId(item.getId())
                .orderStatus(foodOrderDTO.getOrderStatus())
                .amount(foodOrderDTO.getAmount())
                .build();
    }
}
