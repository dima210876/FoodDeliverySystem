package com.itechart.courier_manager.repository;

import com.itechart.courier_manager.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
