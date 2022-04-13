package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.ChangeStatusDTO;
import com.itechart.restaurant_info_service.dto.FoodOrderDTO;
import com.itechart.restaurant_info_service.exception.ChangeOrderStatusException;
import com.itechart.restaurant_info_service.exception.ChangingStatusException;
import com.itechart.restaurant_info_service.exception.ItemNotFoundException;
import com.itechart.restaurant_info_service.model.FoodOrder;
import com.itechart.restaurant_info_service.model.Item;
import com.itechart.restaurant_info_service.model.ItemInOrder;
import com.itechart.restaurant_info_service.model.OrderStatus;
import com.itechart.restaurant_info_service.repository.FoodOrderRepository;
import com.itechart.restaurant_info_service.repository.ItemInOrderRepository;
import com.itechart.restaurant_info_service.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class OrderService {
    private final FoodOrderRepository foodOrderRepository;
    private final ItemInOrderRepository itemInOrderRepository;
    private final ItemRepository itemRepository;
    @LoadBalanced
    private final RestTemplate restTemplate;

    @Transactional
    public FoodOrderDTO addOrder(@Valid FoodOrderDTO foodOrderDTO) throws ItemNotFoundException {
        Optional<Item> optionalItem = itemRepository.findById(foodOrderDTO.getItemId());

        if (optionalItem.isEmpty()) {
            throw new ItemNotFoundException(String.format("Item with id %d not found", foodOrderDTO.getItemId()));
        }

        Item item = optionalItem.get();

        FoodOrder foodOrder = foodOrderRepository.save(FoodOrder.builder()
                .restaurant(item.getRestaurant())
                .restaurantStatus(foodOrderDTO.getOrderStatus().getStatus())
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

    public void setOrderStatusPaid(Long orderId, String newStatus) throws ChangingStatusException {
        Optional<FoodOrder> foodOrderOptional = foodOrderRepository.findById(orderId);

        if (foodOrderOptional.isEmpty()) {
            throw new ChangingStatusException("Couldn't change order status.");
        }

        FoodOrder foodOrder = foodOrderOptional.get();

        OrderStatus currentStatus = OrderStatus.valueOf(foodOrder.getRestaurantStatus().toUpperCase());
        OrderStatus potentialStatus = OrderStatus.valueOf(newStatus.toUpperCase());

        if (currentStatus == OrderStatus.NOT_PAID && potentialStatus != OrderStatus.PAID) {
            throw new ChangingStatusException("Wrong order status.");
        }

        try {
            foodOrder.setRestaurantStatus(potentialStatus.getStatus());
            foodOrderRepository.save(foodOrder);
        } catch (Throwable ex) {
            throw new ChangingStatusException("Couldn't change order status.");
        }
    }

    public void changeOrderStatus(ChangeStatusDTO changeStatusDTO) throws ChangeOrderStatusException {
        Optional<FoodOrder> optionalFoodOrder = foodOrderRepository.findById(changeStatusDTO.getId());
        if (optionalFoodOrder.isEmpty()) {
            throw new ChangeOrderStatusException(String.format("Order with id %d doesn't exist", changeStatusDTO.getId()));
        }

        FoodOrder foodOrder = optionalFoodOrder.get();
        foodOrder.setRestaurantStatus(changeStatusDTO.getRestaurantStatus().toString());
        foodOrderRepository.save(foodOrder);

        final String POST_CHANGE_ORDER_STATUS_URL = "http://FOOD-DELIVERY/changeOrderStatus/" + foodOrder;

        //here always 400 bad request
        ResponseEntity<String> response = restTemplate
                .postForEntity(POST_CHANGE_ORDER_STATUS_URL,changeStatusDTO.getRestaurantStatus().getStatus(),
                        String.class);

        if(!response.getStatusCode().is2xxSuccessful()){
            throw new ChangeOrderStatusException("Couldn't change order status");
        }
    }

    public List<FoodOrder> getAllRestaurantOrders(Long restaurantId) {
        List<FoodOrder> foodOrders = foodOrderRepository.findByRestaurantId(restaurantId);
        for (FoodOrder foodOrder : foodOrders) {
            foodOrder.setOrderPrice(foodOrder.getItemsInOrders().get(0).getItem().getPrice() *
                    foodOrder.getItemsInOrders().get(0).getAmount());

            final String GET_ORDER_URL = "http://FOOD-DELIVERY/getOrderTime/" + foodOrder.getId();

            ResponseEntity<LocalDateTime> response = restTemplate.getForEntity(GET_ORDER_URL, LocalDateTime.class);

            foodOrder.setDeliveryTime(response.getBody());
        }
        return foodOrders;
    }
}
