package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.FoodOrderDTO;
import com.itechart.restaurant_info_service.dto.ItemInOrderDTO;
import com.itechart.restaurant_info_service.dto.StatisticsDTO;
import com.itechart.restaurant_info_service.exception.ChangingStatusException;
import com.itechart.restaurant_info_service.exception.ItemNotFoundException;
import com.itechart.restaurant_info_service.exception.StatisticsException;
import com.itechart.restaurant_info_service.model.*;
import com.itechart.restaurant_info_service.repository.FoodOrderRepository;
import com.itechart.restaurant_info_service.repository.ItemInOrderRepository;
import com.itechart.restaurant_info_service.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
@Validated
public class OrderService {
    private final FoodOrderRepository foodOrderRepository;
    private final ItemInOrderRepository itemInOrderRepository;
    private final ItemRepository itemRepository;
    private final RestTemplate restTemplate;

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

    public StatisticsDTO getRestaurantStatistics(Long restaurantId) throws StatisticsException {
        final String POST_FOR_GET_STATISTICS_BY_ID = "http://FOOD-DELIVERY/getRestaurantStatistics/";

        List<Long> foodOrdersIds = new ArrayList<>();
        List<FoodOrder> restaurantFoodOrders = foodOrderRepository.findAllByRestaurantId(restaurantId);
        for (FoodOrder foodOrder: restaurantFoodOrders) {
            foodOrdersIds.add(foodOrder.getId());
        }
        ResponseEntity<StatisticsDTO> response = restTemplate
                .postForEntity(POST_FOR_GET_STATISTICS_BY_ID, foodOrdersIds, StatisticsDTO.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new StatisticsException("couldn't get info from food delivery service");
        }

        StatisticsDTO statisticsDTO = response.getBody();
        return statisticsDTO;
    }
}
