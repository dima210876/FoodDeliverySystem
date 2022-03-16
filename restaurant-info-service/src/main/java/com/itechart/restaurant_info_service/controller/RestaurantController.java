package com.itechart.restaurant_info_service.controller;

import com.itechart.restaurant_info_service.dto.FeedbackDTO;
import com.itechart.restaurant_info_service.dto.FoodOrderDTO;
import com.itechart.restaurant_info_service.dto.ManagerRegistrationInfoDTO;
import com.itechart.restaurant_info_service.dto.RestaurantDTO;
import com.itechart.restaurant_info_service.exception.ManagerRegistrationException;
import com.itechart.restaurant_info_service.service.FeedbackService;
import com.itechart.restaurant_info_service.service.ManagerService;
import com.itechart.restaurant_info_service.service.OrderService;
import com.itechart.restaurant_info_service.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class RestaurantController {
    private final FeedbackService feedbackService;
    private final RestaurantService restaurantService;
    private final OrderService orderService;
    private final ManagerService managerService;

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

    @PostMapping("/registerManager")
    public void registerManager(@RequestBody ManagerRegistrationInfoDTO managerRegistrationInfoDTO) {
        try {
            managerService.registerManager(managerRegistrationInfoDTO);
        } catch (ManagerRegistrationException e) {
            //TODO log and process exception
        }
    }

    @PostMapping("/newOrder")
    public void addOrder(@RequestBody FoodOrderDTO foodOrderDTO) {
        orderService.addOrder(foodOrderDTO);
    }

    @PostMapping("/newFeedback")
    public void addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.addFeedback(feedbackDTO);
    }
}
