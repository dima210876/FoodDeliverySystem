package com.itechart.courier_manager.dto;

import lombok.Data;

@Data
public class OrganizationDTO {
    private Long id;
    private String name;
    private String accountNumber;
    private String phoneNumber;
    private String officeAddress;
}
