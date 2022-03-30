package com.itechart.courier_manager.dto;

import com.itechart.courier_manager.model.Organization;
import lombok.Data;

@Data
public class CourierManagerResponseDTO {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;
    private OrganizationDTO organization;
}
