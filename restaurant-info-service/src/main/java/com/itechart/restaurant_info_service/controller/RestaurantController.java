package com.itechart.restaurant_info_service.controller;

import com.itechart.restaurant_info_service.dto.*;
import com.itechart.restaurant_info_service.exception.ManagerRegistrationException;
import com.itechart.restaurant_info_service.model.Manager;
import com.itechart.restaurant_info_service.model.Restaurant;
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
import java.util.Map;

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

    @PostMapping("/newFeedback")
    public void addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.addFeedback(feedbackDTO);
    }

    @GetMapping("/getItems")
    public ResponseEntity<Page<ItemDTO>> getItems(@RequestParam String category, int page, int size, String sortColumn, boolean vectorOfSort, String filterName, double filterMinPrice, double filterMaxPrice, String filterRestaurant){
        Pageable pageable;
        if(vectorOfSort)
            pageable = PageRequest.of(page, size, Sort.by(sortColumn).ascending());
        else
            pageable = PageRequest.of(page, size, Sort.by(sortColumn).descending());
        return ResponseEntity.ok().body(itemService.getItems(category, filterName, filterMinPrice, filterMaxPrice, filterRestaurant, pageable));
    }
}
