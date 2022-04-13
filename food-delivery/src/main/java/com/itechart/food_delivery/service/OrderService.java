package com.itechart.food_delivery.service;

import com.itechart.food_delivery.dto.*;
import com.itechart.food_delivery.exception.CreatingRestaurantOrderException;
import com.itechart.food_delivery.exception.OrderCreatingException;
import com.itechart.food_delivery.exception.OrderNotFoundException;
import com.itechart.food_delivery.model.Customer;
import com.itechart.food_delivery.model.Order;
import com.itechart.food_delivery.model.OrderAndFoodOrder;
import com.itechart.food_delivery.repository.OrderAndFoodOrderRepository;
import com.itechart.food_delivery.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final OrderAndFoodOrderRepository orderAndFoodOrderRepository;

    @Transactional
    public CreatedOrderDTO createOrder(@Valid OrderDto orderDto) throws OrderCreatingException {
        CreatedOrderDTO createdOrderDTO;

        try {
            Order order = orderRepository.save(Order.builder()
                    .customer(Customer.builder().userId(orderDto.getCustomerId()).build())
                    .orderAddress(orderDto.getOrderAddress())
                    .orderStatus(orderDto.getOrderStatus())
                    .orderPrice(orderDto.getOrderPrice())
                    .discount(orderDto.getDiscount())
                    .shippingPrice(orderDto.getShippingPrice())
                    .creationTime(orderDto.getCreationTime())
                    .latitude(orderDto.getLatitude())
                    .longitude(orderDto.getLongitude())
                    .build());

            createdOrderDTO = CreatedOrderDTO.builder()
                    .orderId(order.getId())
                    .totalPrice(order.getOrderPrice() + order.getShippingPrice())
                    .build();

            sendRestaurantOrders(orderDto, order);
        } catch (Throwable ex) {
            throw new OrderCreatingException("Couldn't create order");
        }

        return createdOrderDTO;
    }

    @Transactional
    public void sendRestaurantOrders(OrderDto orderDto, Order order) throws CreatingRestaurantOrderException {
        final String POST_FOR_CREATE_RESTAURANT_ORDER = "http://RESTAURANT-INFO-SERVICE/createOrder/";
        //Long i = 1L;
        for (ItemDTO itemDTO : orderDto.getItems()) {

            //delete this when in dto will be correct id
            //itemDTO.setId(i);
            //i++;

            RestaurantOrderDTO restaurantOrderDTO = RestaurantOrderDTO.builder()
                    .itemId(itemDTO.getId())
                    .amount(itemDTO.getCount())
                    .orderStatus(order.getOrderStatus())
                    .build();

            ResponseEntity<RestaurantOrderDTO> response = restTemplate
                    .postForEntity(POST_FOR_CREATE_RESTAURANT_ORDER, restaurantOrderDTO, RestaurantOrderDTO.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new CreatingRestaurantOrderException("couldn't create restaurant order");
            }

            restaurantOrderDTO = response.getBody();

            OrderAndFoodOrder orderAndFoodOrder = OrderAndFoodOrder.builder()
                    .orderId(order.getId())
                    .foodOrderId(restaurantOrderDTO.getId())
                    .foodOrderStatus(restaurantOrderDTO.getOrderStatus())
                    .creationTime(order.getCreationTime())
                    .build();

            orderAndFoodOrderRepository.save(orderAndFoodOrder);
        }
    }

    public LocalDateTime getOrderTime(Long foodOrderId) {
        Optional<OrderAndFoodOrder> orderAndFoodOrderOptional = orderAndFoodOrderRepository.findByFoodOrderId(foodOrderId);
        if (orderAndFoodOrderOptional.isEmpty()) {
            throw new OrderNotFoundException("Order not found");
        }
        return orderRepository.getById(orderAndFoodOrderOptional.get().getOrderId()).getDeliveryTime();
    }

    public OrderAddressesDTO getOrderAddresses(Long orderId){
        final String POST_FOR_RESTAURANT_ADDRESSES = "http://RESTAURANT-INFO-SERVICE/getRestaurantAddresses/";

        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if(orderOptional.isEmpty()){
            throw new OrderNotFoundException("Order not found");
        }

        Order order = orderOptional.get();

        List<OrderAndFoodOrder> foodOrders = orderAndFoodOrderRepository.findAllByOrderId(orderId);
        List<Long> foodOrderIds = getFoodOrderIds(foodOrders);

        ResponseEntity<RestaurantAddressesDTO> response = restTemplate.postForEntity(POST_FOR_RESTAURANT_ADDRESSES,
                foodOrderIds, RestaurantAddressesDTO.class);

        if(!response.getStatusCode().is2xxSuccessful()){
            throw new OrderNotFoundException("Couldn'd find restaurant address");
        }

        RestaurantAddressesDTO restaurantAddressesDTO = response.getBody();

        return OrderAddressesDTO.builder()
                .deliveryAddress(order.getOrderAddress())
                .restaurantAddresses(restaurantAddressesDTO.getAddresses())
                .build();
    }

    private List<Long> getFoodOrderIds(List<OrderAndFoodOrder> orders){
        List<Long> ids = new ArrayList<>();

        for(OrderAndFoodOrder order: orders){
            ids.add(order.getFoodOrderId());
        }

        return ids;
    }
}
