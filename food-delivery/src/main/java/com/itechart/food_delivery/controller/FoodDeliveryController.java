package com.itechart.food_delivery.controller;

import com.itechart.food_delivery.dto.CustomerDTO;
import com.itechart.food_delivery.dto.OrderDto;
import com.itechart.food_delivery.exception.CustomerRegistrationException;
import com.itechart.food_delivery.exception.GettingAllOrdersException;
import com.itechart.food_delivery.model.Customer;
import com.itechart.food_delivery.model.Order;
import com.itechart.food_delivery.service.CustomerService;
import com.itechart.food_delivery.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class FoodDeliveryController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @PostMapping("/registerCustomer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerRegistrationException {
        return ResponseEntity.ok().body(customerService.registerCustomer(customerDTO));
    }

    @PostMapping("/postNewOrder")
    public ResponseEntity<Order> postNewOrder(@RequestBody OrderDto orderDTO) {
        return ResponseEntity.ok().body(orderService.postNewOrder(orderDTO));
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<Order> getAllOrders() throws GettingAllOrdersException {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }
}
