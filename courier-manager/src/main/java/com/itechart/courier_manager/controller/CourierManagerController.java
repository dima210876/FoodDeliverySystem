package com.itechart.courier_manager.controller;

import com.itechart.courier_manager.dto.CourierDto;
import com.itechart.courier_manager.dto.CourierManagerDTO;
import com.itechart.courier_manager.exception.CourierRegistrationException;
import com.itechart.courier_manager.model.Courier;
import com.itechart.courier_manager.model.CourierManager;
import com.itechart.courier_manager.service.CourierManagerService;
import com.itechart.courier_manager.service.CourierService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CourierManagerController {
    private final CourierService courierService;
    private final CourierManagerService courierManagerService;

    @PostMapping("/registerCourier")
    public ResponseEntity<Courier> registerCourier(@RequestBody CourierDto courierDto) throws CourierRegistrationException {
        return ResponseEntity.ok().body(courierService.registerCourier(courierDto));
    }

    @PostMapping("/registerCourierManager")
    public ResponseEntity<CourierManager> registerCourierManager(@RequestBody CourierManagerDTO courierManagerDTO) throws CourierRegistrationException {
        return ResponseEntity.ok().body(courierManagerService.registerCourierManager(courierManagerDTO));

    }

//    @GetMapping("/getManagerInfo")
//    public ResponseEntity<CourierManager> getManagerInfo(@RequestParam("id") Long managerId) throws CourierRegistrationException {
//        return ResponseEntity.ok().body(courierManagerService.getManagerInfo(managerId));
//    }
}
