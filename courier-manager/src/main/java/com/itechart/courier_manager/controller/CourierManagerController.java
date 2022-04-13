package com.itechart.courier_manager.controller;

import com.itechart.courier_manager.dto.CourierDto;
import com.itechart.courier_manager.dto.CourierManagerDTO;
import com.itechart.courier_manager.dto.CourierOrderDto;
import com.itechart.courier_manager.dto.OrganizationDTO;
import com.itechart.courier_manager.exception.CourierRegistrationException;
import com.itechart.courier_manager.exception.EditOrganizationException;
import com.itechart.courier_manager.exception.GettingInfoException;
import com.itechart.courier_manager.model.Courier;
import com.itechart.courier_manager.model.CourierManager;
import com.itechart.courier_manager.model.CourierOrder;
import com.itechart.courier_manager.model.Organization;
import com.itechart.courier_manager.service.CourierManagerService;
import com.itechart.courier_manager.service.CourierOrderService;
import com.itechart.courier_manager.service.CourierService;
import com.itechart.courier_manager.service.OrganizationService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@AllArgsConstructor
public class CourierManagerController {
    private final CourierService courierService;
    private final CourierManagerService courierManagerService;
    private final OrganizationService organizationService;
    private final CourierOrderService courierOrderService;

    @PostMapping("/registerCourier")
    public ResponseEntity<Courier> registerCourier(@RequestBody CourierDto courierDto) throws CourierRegistrationException {
        return ResponseEntity.ok().body(courierService.registerCourier(courierDto));
    }

    @PostMapping("/registerCourierManager")
    public ResponseEntity<CourierManager> registerCourierManager(@RequestBody CourierManagerDTO courierManagerDTO) throws CourierRegistrationException {
        return ResponseEntity.ok().body(courierManagerService.registerCourierManager(courierManagerDTO));

    }

    @PostMapping("/editOrganizationInfo")
    public ResponseEntity<Organization> editRestaurantInfo(@RequestBody OrganizationDTO organizationDTO) throws EditOrganizationException {
        return ResponseEntity.ok().body(organizationService.editOrganizationInfo(organizationDTO));
    }

    @GetMapping("/getManagerInfo")
    public ResponseEntity<CourierManager> getManagerInfo(@RequestParam("id") Long managerId) throws GettingInfoException {
        return ResponseEntity.ok().body(courierManagerService.getManagerInfo(managerId));
    }

    @GetMapping("/getCourierInfo")
    public ResponseEntity<Courier> getCourierInfo(@RequestParam("id") Long courierId) throws GettingInfoException {
        return ResponseEntity.ok().body(courierService.getCourierInfo(courierId));
    }

    @GetMapping("/getUnoccupiedCouriers")
    public ResponseEntity<List<Courier>> getUnoccupiedCouriers(@RequestParam("email") String email){
        return ResponseEntity.ok().body(courierService.getUnoccupiedCouriers(email));
    }

    @PostMapping("/changeStatus")
    public ResponseEntity<CourierOrder> changeStatus(@RequestBody CourierOrderDto courierOrderDto) throws URISyntaxException {
        return ResponseEntity.ok().body(courierOrderService.addCourierOrder(courierOrderDto));
    }
}
