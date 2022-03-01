package com.itechart.restaurant_info_service.controller;

import com.itechart.restaurant_info_service.dto.FeedbackDTO;
import com.itechart.restaurant_info_service.dto.FoodOrderDTO;
import com.itechart.restaurant_info_service.dto.RestaurantDTO;
import com.itechart.restaurant_info_service.service.FeedbackService;
import com.itechart.restaurant_info_service.service.OrderService;
import com.itechart.restaurant_info_service.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@AllArgsConstructor
public class RestaurantController {
    private final FeedbackService feedbackService;
    private final RestaurantService restaurantService;
    private final OrderService orderService;

    @PostMapping("/newRestaurant")
    public void addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        restaurantService.addRestaurant(restaurantDTO);
    }

    @PostMapping("/newOrder")
    public void addOrder(@RequestBody FoodOrderDTO foodOrderDTO){
        orderService.addOrder(foodOrderDTO);
    }

    @PostMapping("/newFeedback")
    public void addFeedback(@RequestBody FeedbackDTO feedbackDTO){
        feedbackService.addFeedback(feedbackDTO);
    }
}
