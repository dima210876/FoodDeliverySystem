package com.itechart.restaurant_info_service.controller;

import com.itechart.restaurant_info_service.dto.*;
import com.itechart.restaurant_info_service.exception.ManagerRegistrationException;
import com.itechart.restaurant_info_service.model.Manager;
import com.itechart.restaurant_info_service.service.*;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class RestaurantController {
    private final FeedbackService feedbackService;
    private final RestaurantService restaurantService;
    private final OrderService orderService;
    private final ManagerService managerService;
    private final ItemService itemService;
    private AwsService awsService;


    @PostMapping("/registerManager")
    public ResponseEntity<Manager> registerManager(@RequestBody ManagerRegistrationInfoDTO managerRegistrationInfoDTO) throws ManagerRegistrationException {
        return ResponseEntity.ok().body(managerService.registerManager(managerRegistrationInfoDTO));
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

    @PostMapping("/newItem")
    public void addItem(@RequestBody NewItemDTO newItemDTO, /*Set<IngredientDTO> ingredients,*/@RequestParam(value = "manager") ManagerDTO managerDTO/*, MultipartFile image*/){
        System.out.println(managerDTO.getEmail());
        System.out.println(newItemDTO.getName());
        System.out.println(newItemDTO.getDescription());
        itemService.addItem(newItemDTO, /*ingredients,*/ managerDTO/*, image*/);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(awsService.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = awsService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(awsService.deleteFile(fileName), HttpStatus.OK);
    }
}
