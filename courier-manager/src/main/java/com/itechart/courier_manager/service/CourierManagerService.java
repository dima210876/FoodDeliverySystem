package com.itechart.courier_manager.service;

import com.itechart.courier_manager.config.DeletingUserConfig;
import com.itechart.courier_manager.dto.CourierManagerDTO;
import com.itechart.courier_manager.dto.IdentityRegistrationDTO;
import com.itechart.courier_manager.exception.CourierRegistrationException;
import com.itechart.courier_manager.model.CourierManager;
import com.itechart.courier_manager.model.Organization;
import com.itechart.courier_manager.repository.CourierManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.sql.SQLException;

@Service
@AllArgsConstructor
@Validated
public class CourierManagerService {
    private final CourierManagerRepository courierManagerRepository;
    private final OrganizationService organizationService;
    private RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public CourierManager registerCourierManager(@Valid CourierManagerDTO courierManagerDTO) throws CourierRegistrationException {
        final String IDENTITY_REGISTER_URL = "http://localhost:8081/register";
        final String ROLE_COURIER_SERVICE_MANAGER = "ROLE_COURIER_SERVICE_MANAGER";

        IdentityRegistrationDTO identityRegistrationDTO = IdentityRegistrationDTO.builder()
                .email(courierManagerDTO.getEmail())
                .password(courierManagerDTO.getPassword())
                .firstName(courierManagerDTO.getFirstName())
                .lastName(courierManagerDTO.getLastName())
                .role(ROLE_COURIER_SERVICE_MANAGER)
                .build();

//        ResponseEntity<IdentityRegistrationDTO> response = restTemplate.
//                postForEntity(IDENTITY_REGISTER_URL, identityRegistrationDTO, IdentityRegistrationDTO.class);
//
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new CourierRegistrationException("Identity service couldn't register the manager");
//        }
//
//        Long userId = response.getBody().getId();
        Long userId = 1L;

        Organization organization = organizationService.createDefaultOrganization(courierManagerDTO.getOrganizationName());

        CourierManager courierManager = CourierManager.builder()
                .userId(userId)
                .email(courierManagerDTO.getEmail())
                .firstName(courierManagerDTO.getFirstName())
                .lastName(courierManagerDTO.getLastName())
                .phoneNumber(courierManagerDTO.getPhoneNumber())
                .role(ROLE_COURIER_SERVICE_MANAGER)
                .organization(organization)
                .build();

        try {
            courierManager = courierManagerRepository.save(courierManager);
        } catch (RuntimeException exception) {
            rabbitTemplate.convertAndSend(
                    DeletingUserConfig.EXCHANGE,
                    DeletingUserConfig.ROUTING_KEY,
                    courierManager.getUserId()
            );
            throw new CourierRegistrationException(exception.getMessage());
        }

        return courierManager;
    }
}
