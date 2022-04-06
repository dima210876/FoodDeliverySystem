package com.itechart.food_delivery.controller;

import com.itechart.food_delivery.dto.CustomerDTO;
import com.itechart.food_delivery.exception.CustomerRegistrationException;
import com.itechart.food_delivery.model.Customer;
import com.itechart.food_delivery.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@AllArgsConstructor
public class FoodDeliveryController {
    private final CustomerService customerService;

    @PostMapping("/registerCustomer")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerRegistrationException {
        return ResponseEntity.ok().body(customerService.registerCustomer(customerDTO));
    }
}
