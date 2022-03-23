package com.itechart.courier_manager.controller;

import com.itechart.courier_manager.dto.CourierDto;
import com.itechart.courier_manager.dto.CourierManagerDTO;
import com.itechart.courier_manager.exception.CourierRegistrationException;
import com.itechart.courier_manager.model.Courier;
import com.itechart.courier_manager.model.CourierManager;
import com.itechart.courier_manager.service.CourierManagerService;
import com.itechart.courier_manager.service.CourierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CourierManagerController {
    private final CourierService courierService;
    private final CourierManagerService courierManagerService;

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

    @PostMapping("/registerCourier")
    public ResponseEntity<Courier> registerCourier(@RequestBody CourierDto courierDto) throws CourierRegistrationException {
        return ResponseEntity.ok().body(courierService.registerCourier(courierDto));
    }

    @PostMapping("/registerCourierManager")
    public ResponseEntity<CourierManager> registerCourierManager(@RequestBody CourierManagerDTO courierManagerDTO) throws CourierRegistrationException {
        return ResponseEntity.ok().body(courierManagerService.registerCourierManager(courierManagerDTO));

    }
}
