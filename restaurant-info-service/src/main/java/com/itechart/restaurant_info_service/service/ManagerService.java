package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.IdentityRegistrationDTO;
import com.itechart.restaurant_info_service.dto.ManagerRegistrationInfoDTO;
import com.itechart.restaurant_info_service.exception.ManagerRegistrationException;
import com.itechart.restaurant_info_service.model.Manager;
import com.itechart.restaurant_info_service.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Service
@Validated
@AllArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final RestaurantService restaurantService;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public Manager registerManager(@Valid ManagerRegistrationInfoDTO managerRegistrationInfoDTO) throws ManagerRegistrationException {
        final String IDENTITY_REGISTER_URL = "http://localhost:8081/register";
        final String ROLE_MANAGER = "ROLE_MANAGER";

        IdentityRegistrationDTO identityRegistrationDTO = IdentityRegistrationDTO.builder()
                .email(managerRegistrationInfoDTO.getEmail())
                .password(managerRegistrationInfoDTO.getPassword())
                .firstName(managerRegistrationInfoDTO.getFirstName())
                .lastName(managerRegistrationInfoDTO.getLastName())
                .role(ROLE_MANAGER)
                .build();

        ResponseEntity<IdentityRegistrationDTO> response = restTemplate.
                postForEntity(IDENTITY_REGISTER_URL, identityRegistrationDTO, IdentityRegistrationDTO.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new ManagerRegistrationException("Identity service couldn't register the manager");
        }

        Long userId = response.getBody().getId();

        Manager manager = Manager.builder()
                .userId(userId)
                .email(managerRegistrationInfoDTO.getEmail())
                .firstName(managerRegistrationInfoDTO.getFirstName())
                .lastName(managerRegistrationInfoDTO.getLastName())
                .phoneNumber(managerRegistrationInfoDTO.getPhoneNumber())
                .role(ROLE_MANAGER)
                .build();

        manager = managerRepository.saveAndFlush(manager);

        restaurantService.createDefaultRestaurant(manager, managerRegistrationInfoDTO.getRestaurantName());

        return manager;
    }

}
