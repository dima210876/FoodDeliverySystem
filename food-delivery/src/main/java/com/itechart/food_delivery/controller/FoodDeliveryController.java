package com.itechart.food_delivery.controller;

import com.itechart.food_delivery.dto.CreatedOrderDTO;
import com.itechart.food_delivery.dto.CustomerDTO;
import com.itechart.food_delivery.dto.OrderAddressesDTO;
import com.itechart.food_delivery.dto.OrderDto;
import com.itechart.food_delivery.dto.StatisticsDTO;
import com.itechart.food_delivery.dto.ReadyOrderDTO;
import com.itechart.food_delivery.exception.*;
import com.itechart.food_delivery.model.Customer;
import com.itechart.food_delivery.model.Order;
import com.itechart.food_delivery.service.CustomerService;
import com.itechart.food_delivery.service.FoodDeliveryService;
import com.itechart.food_delivery.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.awt.*;
import java.util.List;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@AllArgsConstructor
public class FoodDeliveryController {
    private final CustomerService customerService;
    private final OrderService orderService;
    private final FoodDeliveryService foodDeliveryService;

    @PostMapping("/registerCustomer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerRegistrationException {
        return ResponseEntity.ok().body(customerService.registerCustomer(customerDTO));
    }

    @GetMapping("/getCustomerInfo")
    public ResponseEntity<Customer> getCustomerInfo(@RequestParam("id") Long customerId) throws GettingInfoException {
        return ResponseEntity.ok().body(customerService.getCustomerInfo(customerId));
    }

    @PostMapping("/createOrder")
    public ResponseEntity<CreatedOrderDTO> createOrder(@RequestBody OrderDto orderDto)
            throws OrderCreatingException {
        return ResponseEntity.ok().body(orderService.createOrder(orderDto));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok().body(foodDeliveryService.getOrder(orderId));
    }

    @GetMapping("/getOrderTime/{orderId}")
    public ResponseEntity<LocalDateTime> getOrderTime(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderService.getOrderTime(orderId));
    }

    @PostMapping("/changeOrderStatus/{orderId}")
    public ResponseEntity<OrderDto> changeOrderStatus(@PathVariable Long orderId, @RequestBody String newStatus)
            throws OrderStatusChangeException, OrderNotFoundException {
        foodDeliveryService.changeOrderStatus(orderId, newStatus);
        return ResponseEntity.ok().body(foodDeliveryService.getOrder(orderId));
    }

    @PostMapping("/getRestaurantStatistics")
    public ResponseEntity<StatisticsDTO> getRestaurantStatistics(@RequestBody List<Long> ordersIds) throws StatisticsException {
        return ResponseEntity.ok().body(orderService.getRestaurantStatistics(ordersIds));
    }

    @GetMapping("/readyOrders")
    public ResponseEntity<List<ReadyOrderDTO>> getReadyOrders(){
        return ResponseEntity.ok().body(orderService.getReadyOrders());
    }

    @PostMapping("/changeOrderStatus")
    public ResponseEntity<String> changeOrderStatus(@RequestBody Long id) {
        return ResponseEntity.ok().body(orderService.changeOrderStatus(id));
    }
      
    @PostMapping("/changeFoodOrderStatus/{foodOrderId}")
    public void changeFoodOrderStatus(@PathVariable Long foodOrderId, @RequestBody String newStatus)
            throws OrderStatusChangeException, OrderNotFoundException {
        foodDeliveryService.changeFoodOrderStatus(foodOrderId, newStatus);
    }

    @PostMapping("/getOrderAddresses/{orderId}")
    public ResponseEntity<OrderAddressesDTO> getOrderAddresses(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok().body(orderService.getOrderAddresses(orderId));
    }
}
