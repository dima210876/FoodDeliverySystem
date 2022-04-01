package com.itechart.courier_manager.service;

import com.itechart.courier_manager.dto.OrganizationDTO;
import com.itechart.courier_manager.exception.EditOrganizationException;
import com.itechart.courier_manager.model.Organization;
import com.itechart.courier_manager.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    static final String DEFAULT_ADDRESS = "Unknown";

    @Transactional
    public Organization createDefaultOrganization(String organizationName) throws RuntimeException {
        try {
            Organization organization = Organization.builder()
                    .name(organizationName)
                    .accountNumber(null)
                    .officeAddress(DEFAULT_ADDRESS)
                    .latitude(0D)
                    .longitude(0D)
                    .build();

            organization = organizationRepository.save(organization);
            return organization;
        } catch (Throwable exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Transactional
    public Organization editOrganizationInfo(@Valid OrganizationDTO organizationDTO) throws EditOrganizationException {
        Organization organization;

        try {
            Optional<Organization> optionalOrganization = organizationRepository
                    .findById(organizationDTO.getOrganizationId());

            if (optionalOrganization.isEmpty()) {
                throw new EditOrganizationException(String.format("Organization with id %d doesn't exist", organizationDTO.getOrganizationId()));
            }

            organization = optionalOrganization.get();

            organization.setName(organizationDTO.getName());
            organization.setAccountNumber(organizationDTO.getAccountNumber());
            organization.setPhoneNumber(organizationDTO.getPhoneNumber());
            organization.setOfficeAddress(organizationDTO.getOfficeAddress());
            organization.setLatitude(organizationDTO.getLatitude());
            organization.setLongitude(organizationDTO.getLongitude());

            organization = organizationRepository.save(organization);

        } catch (Throwable ex) {
            throw new EditOrganizationException(ex.getMessage());
        }
        return organization;
    }
}
