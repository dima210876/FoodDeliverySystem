package com.itechart.courier_manager.service;

import com.itechart.courier_manager.dto.CourierDto;
import com.itechart.courier_manager.dto.IdentityRegistrationDTO;
import com.itechart.courier_manager.exception.CourierRegistrationException;
import com.itechart.courier_manager.model.Courier;
import com.itechart.courier_manager.model.Organization;
import com.itechart.courier_manager.repository.CourierRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Service
@AllArgsConstructor
@Validated
public class CourierService {
    private final CourierRepository courierRepository;
    private RestTemplate restTemplate;

    @Transactional

    public Courier registerCourier(@Valid CourierDto courierDto) throws CourierRegistrationException {
        final String IDENTITY_REGISTER_URL = "http://localhost:8081/register";
        final String ROLE_COURIER = "ROLE_COURIER";

        IdentityRegistrationDTO identityRegistrationDTO = IdentityRegistrationDTO.builder()
                .email(courierDto.getEmail())
                .password(courierDto.getPassword())
                .firstName(courierDto.getFirstName())
                .lastName(courierDto.getLastName())
                .role(ROLE_COURIER)
                .build();

        ResponseEntity<IdentityRegistrationDTO> response = restTemplate
                .postForEntity(IDENTITY_REGISTER_URL, identityRegistrationDTO, IdentityRegistrationDTO.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CourierRegistrationException("Identity service couldn't register the manager");
        }

        Long userId = response.getBody().getId();

        Courier courier = Courier.builder()
                .userId(userId)
                .email(courierDto.getEmail())
                .firstName(courierDto.getFirstName())
                .lastName(courierDto.getLastName())
                .phoneNumber(courierDto.getLastName())
                .organization(Organization.builder().id(courierDto.getOrganizationId()).build())
                .role(ROLE_COURIER)
                .build();

        return courierRepository.save(courier);
    }
}
