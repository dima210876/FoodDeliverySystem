package com.itechart.food_delivery.controller;

import com.itechart.food_delivery.dto.CustomerDTO;
import com.itechart.food_delivery.dto.OrderDto;
import com.itechart.food_delivery.exception.CustomerRegistrationException;
import com.itechart.food_delivery.exception.OrderNotFoundException;
import com.itechart.food_delivery.exception.OrderStatusChangeException;
import com.itechart.food_delivery.model.Customer;
import com.itechart.food_delivery.service.CustomerService;
import com.itechart.food_delivery.service.FoodDeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class FoodDeliveryController {
  
    private final CustomerService customerService;
    private final FoodDeliveryService foodDeliveryService;

    @PostMapping("/registerCustomer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO)
            throws CustomerRegistrationException
    {
        return ResponseEntity.ok().body(customerService.registerCustomer(customerDTO));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws OrderNotFoundException
    {
        return ResponseEntity.ok().body(foodDeliveryService.getOrder(orderId));
    }

    @PostMapping("/changeOrderStatus/{orderId}")
    public ResponseEntity<OrderDto> changeOrderStatus(@PathVariable Long orderId, @RequestBody String newStatus)
            throws OrderStatusChangeException, OrderNotFoundException
    {
        foodDeliveryService.changeOrderStatus(orderId, newStatus);
        return ResponseEntity.ok().body(foodDeliveryService.getOrder(orderId));
    }
}
