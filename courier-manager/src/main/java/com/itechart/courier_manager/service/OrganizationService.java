package com.itechart.courier_manager.service;

import com.itechart.courier_manager.config.DeletingUserConfig;
import com.itechart.courier_manager.exception.CourierRegistrationException;
import com.itechart.courier_manager.model.Organization;
import com.itechart.courier_manager.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public Organization createDefaultOrganization(String organizationName) throws CourierRegistrationException {
        final String DEFAULT_ACCOUNT_NUMBER = "0";
        final String DEFAULT_ADDRESS = "Unknown";

        Organization organization = Organization.builder()
                .name(organizationName)
                .accountNumber(DEFAULT_ACCOUNT_NUMBER)
                .officeAddress(DEFAULT_ADDRESS)
                .build();

        try {
            organization = organizationRepository.save(organization);;
        } catch (RuntimeException exception) {
            throw new CourierRegistrationException(exception.getMessage());
        }

        return organization;
    }
}
