package com.itechart.restaurant_info_service.controller;

import com.itechart.restaurant_info_service.dto.*;
import com.itechart.restaurant_info_service.exception.ChangeOrderStatusException;
import com.itechart.restaurant_info_service.exception.EditRestaurantException;
import com.itechart.restaurant_info_service.exception.GettingInfoException;
import com.itechart.restaurant_info_service.exception.ManagerRegistrationException;
import com.itechart.restaurant_info_service.model.FoodOrder;
import com.itechart.restaurant_info_service.model.Manager;
import com.itechart.restaurant_info_service.model.Restaurant;
import com.itechart.restaurant_info_service.model.RestaurantStatus;
import com.itechart.restaurant_info_service.service.FeedbackService;
import com.itechart.restaurant_info_service.service.ManagerService;
import com.itechart.restaurant_info_service.service.OrderService;
import com.itechart.restaurant_info_service.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@AllArgsConstructor
public class RestaurantController {
    private final FeedbackService feedbackService;
    private final RestaurantService restaurantService;
    private final OrderService orderService;
    private final ManagerService managerService;

    @PostMapping("/registerManager")
    public ResponseEntity<Manager> registerManager(@RequestBody ManagerRegistrationInfoDTO managerRegistrationInfoDTO) throws ManagerRegistrationException {
        return ResponseEntity.ok().body(managerService.registerManager(managerRegistrationInfoDTO));
    }

    @PostMapping("/editRestaurantInfo")
    public ResponseEntity<Restaurant> editRestaurantInfo(@RequestBody RestaurantDTO restaurantDTO) throws EditRestaurantException {
        return ResponseEntity.ok().body(restaurantService.editRestaurantInfo(restaurantDTO));
    }

    @GetMapping("/getManagerInfo")
    public ResponseEntity<Manager> getManagerInfo(@RequestParam("id") Long managerId) throws GettingInfoException {
        return ResponseEntity.ok().body(managerService.getManagerInfo(managerId));
    }

    @PostMapping("/newOrder")
    public void addOrder(@RequestBody FoodOrderDTO foodOrderDTO) {
        orderService.addOrder(foodOrderDTO);
    }

    @PostMapping("/changeOrderStatus")
    public void changeOrderStatus(@RequestBody ChangeStatusDTO changeStatusDTO) throws ChangeOrderStatusException {
        orderService.changeOrderStatus(changeStatusDTO);
    }

    @GetMapping("/getAllRestaurantOrders")
    public ResponseEntity<List<FoodOrder>> getAllRestaurantOrders(@RequestParam("id") Long restaurantId) {
        return ResponseEntity.ok().body(orderService.getAllRestaurantOrders(restaurantId));
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<FoodOrder>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @PostMapping("/newFeedback")
    public void addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.addFeedback(feedbackDTO);
    }

}
