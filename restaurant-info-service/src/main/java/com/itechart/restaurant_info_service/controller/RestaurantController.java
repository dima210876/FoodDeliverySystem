package com.itechart.restaurant_info_service.controller;

import com.itechart.restaurant_info_service.dto.*;
import com.itechart.restaurant_info_service.exception.*;
import com.itechart.restaurant_info_service.model.Manager;
import com.itechart.restaurant_info_service.model.Restaurant;
import com.itechart.restaurant_info_service.service.*;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@AllArgsConstructor
public class RestaurantController {
    private final FeedbackService feedbackService;
    private final RestaurantService restaurantService;
    private final OrderService orderService;
    private final ManagerService managerService;
    private final ItemService itemService;
    private final AwsService awsService;

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

    @PostMapping("/createOrder")
    public ResponseEntity<FoodOrderDTO> addOrder(@RequestBody FoodOrderDTO foodOrderDTO) throws ItemNotFoundException {
        return ResponseEntity.ok().body(orderService.addOrder(foodOrderDTO));
    }

    @PostMapping("/changeOrderStatus/{orderId}")
    public ResponseEntity<String> changeOrderStatus(@PathVariable Long orderId, @RequestBody String newStatus) throws ChangingStatusException {
        orderService.changeOrderStatus(orderId, newStatus);
        return new ResponseEntity<>("Status has changed", HttpStatus.OK);
    }

    @PostMapping("/newFeedback")
    public void addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.addFeedback(feedbackDTO);
    }

    @GetMapping("/getItems")
    public ResponseEntity<Page<ItemDTO>> getItems(@RequestParam String category, int page, int size, String sortColumn, boolean vectorOfSort, String filterName, double filterMinPrice, double filterMaxPrice, String filterRestaurant) {
        Pageable pageable;
        if (vectorOfSort)
            pageable = PageRequest.of(page, size, Sort.by(sortColumn).ascending());
        else
            pageable = PageRequest.of(page, size, Sort.by(sortColumn).descending());
        return ResponseEntity.ok().body(itemService.getItems(category, filterName, filterMinPrice, filterMaxPrice, filterRestaurant, pageable));
    }

    @PostMapping(value = "/newItem")
    public ResponseEntity<Long> addItem(@RequestBody NewItemDTO newItemDTO){
        return ResponseEntity.ok().body(itemService.addItem(newItemDTO));

    }

    @PostMapping(value = "/addImage",  headers = "content-type=multipart/*")
    public void addImage(@RequestParam(value = "image") MultipartFile image, @RequestParam(value = "id") Long id){
        itemService.addImage(image, id);   
    }

    @GetMapping("/getRestaurantStatistics")
    public ResponseEntity<StatisticsDTO> getRestaurantStatistics(@RequestParam("id") Long restaurantId) throws StatisticsException {
        return ResponseEntity.ok().body(orderService.getRestaurantStatistics(restaurantId));
    }
}
