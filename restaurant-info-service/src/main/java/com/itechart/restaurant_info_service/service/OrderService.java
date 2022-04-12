package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.FoodOrderDTO;
import com.itechart.restaurant_info_service.dto.ItemInOrderDTO;
import com.itechart.restaurant_info_service.exception.ChangingStatusException;
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

    public void changeOrderStatus(Long orderId, String newStatus) throws ChangingStatusException {
        Optional<FoodOrder> foodOrderOptional = foodOrderRepository.findById(orderId);

        if (foodOrderOptional.isEmpty()) {
            throw new ChangingStatusException("Couldn't change order status.");
        }

        FoodOrder foodOrder = foodOrderOptional.get();

        OrderStatus currentStatus = OrderStatus.valueOf(foodOrder.getRestaurantStatus().toUpperCase());
        OrderStatus potentialStatus = OrderStatus.valueOf(newStatus.toUpperCase());

        switch (currentStatus) {
            case NOT_PAID:
                if (potentialStatus != OrderStatus.PAID) {
                    throw new ChangingStatusException("Wrong order status.");
                }
                break;
            case PAID:
                if (potentialStatus != OrderStatus.COOKING) {
                    throw new ChangingStatusException("Wrong order status.");
                }
                break;
            case COOKING:
                if (potentialStatus != OrderStatus.READY) {
                    throw new ChangingStatusException("Wrong order status.");
                }
                break;
        }

        try{
            foodOrder.setRestaurantStatus(potentialStatus.getStatus());
            foodOrderRepository.save(foodOrder);
        } catch (Throwable ex){
            throw new ChangingStatusException("Couldn't change order status.");
        }
    }
}
