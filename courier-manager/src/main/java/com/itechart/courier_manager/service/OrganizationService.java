package com.itechart.courier_manager.service;

import com.itechart.courier_manager.dto.OrganizationDto;
import com.itechart.courier_manager.model.Organization;
import com.itechart.courier_manager.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public void addOrganization(OrganizationDto organizationDto) {
        Organization organization = Organization.builder()
                .name(organizationDto.getName())
                .accountNumber(organizationDto.getAccountNumber())
                .phoneNumber(organizationDto.getPhoneNumber())
                .officeAddress(organizationDto.getOfficeAddress())
                .couriers(organizationDto.getCouriers())
                .build();
        organizationRepository.save(organization);
    }
}
