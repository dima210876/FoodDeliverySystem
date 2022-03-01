package com.itechart.courier_manager.service;

import com.itechart.courier_manager.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
}
