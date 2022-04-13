package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.ChangeStatusDTO;
import com.itechart.restaurant_info_service.dto.FoodOrderDTO;
import com.itechart.restaurant_info_service.dto.ItemInOrderDTO;
import com.itechart.restaurant_info_service.exception.ChangeOrderStatusException;
import com.itechart.restaurant_info_service.model.*;
import com.itechart.restaurant_info_service.repository.FoodOrderRepository;
import com.itechart.restaurant_info_service.repository.ItemInOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;

@Service
@AllArgsConstructor
@Validated
public class OrderService {
    private final FoodOrderRepository foodOrderRepository;
    private final ItemInOrderRepository itemInOrderRepository;

    public void addOrder(@Valid FoodOrderDTO foodOrderDTO) {
        //TODO do we need to check if the item is in restaurant's menu
        //TODO check if item is available
        //TODO amount validation

        FoodOrder foodOrder = FoodOrder.builder()
                .restaurant(Restaurant.builder().id(foodOrderDTO.getRestaurantId()).build())
                .restaurantStatus(RestaurantStatus.VERIFICATION.toString())
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

    public void changeOrderStatus(ChangeStatusDTO changeStatusDTO) throws ChangeOrderStatusException {
        Optional<FoodOrder> optionalFoodOrder = foodOrderRepository.findById(changeStatusDTO.getId());
        if (optionalFoodOrder.isEmpty()) {
            throw new ChangeOrderStatusException(String.format("Order with id %d doesn't exist", changeStatusDTO.getId()));
        }

        FoodOrder foodOrder = optionalFoodOrder.get();
        foodOrder.setRestaurantStatus(changeStatusDTO.getRestaurantStatus().toString());
        foodOrderRepository.save(foodOrder);

        // TODO: Invoke method of changing restaurant status from delivery service
        // (as soon as another ticket will be ready)
    }

    public List<FoodOrder> getAllRestaurantOrders(Long restaurantId) {
        List<FoodOrder> foodOrders = foodOrderRepository.findByRestaurantId(restaurantId);
        for(FoodOrder foodOrder: foodOrders){
            foodOrder.setOrderPrice(foodOrder.getItemsInOrders().get(0).getItem().getPrice() *
                    Double.valueOf(foodOrder.getItemsInOrders().get(0).getAmount()));
        }
        return foodOrders;
    }

    public List<FoodOrder> getAllOrders() {
        List<FoodOrder> foodOrders = foodOrderRepository.findAll();
        return foodOrders;
    }
}
