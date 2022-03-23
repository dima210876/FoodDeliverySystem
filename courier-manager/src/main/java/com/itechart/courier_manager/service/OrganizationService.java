package com.itechart.courier_manager.service;

import com.itechart.courier_manager.model.CourierManager;
import com.itechart.courier_manager.model.Organization;
import com.itechart.courier_manager.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public Organization createDefaultOrganization(String organizationName) {
        final String DEFAULT_ACCOUNT_NUMBER = "0";
        final String DEFAULT_ADDRESS = "Unknown";

        Organization organization = Organization.builder()
                .name(organizationName)
                .accountNumber(DEFAULT_ACCOUNT_NUMBER)
                .officeAddress(DEFAULT_ADDRESS)
                .build();

        return organizationRepository.save(organization);
    }
}
