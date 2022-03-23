package com.itechart.food_delivery.controller;

import com.itechart.food_delivery.dto.CustomerDTO;
import com.itechart.food_delivery.exception.CustomerRegistrationException;
import com.itechart.food_delivery.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class FoodDeliveryController {
    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->
        {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @PostMapping("/registerCustomer")
    public void registerCourier(@RequestBody CustomerDTO customerDTO) {
        try {
            customerService.registerCustomer(customerDTO);
        } catch (CustomerRegistrationException e) {
            //TODO add log and process exception
        }
    }
}
