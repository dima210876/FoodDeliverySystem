package com.itechart.courier_manager.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OrganizationDTO {
    @NotNull(message = "Organization id is required")
    private Long organizationId;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(min = 2, max = 100, message = "Name string length limits exceeded")
    private String name;

    @Size(max = 34, message = "Account number string length limits exceeded")
    private String accountNumber;

    @Size(max = 15, message = "Phone number string length limits exceeded")
    private String phoneNumber;

    @NotNull(message = "Office address is required")
    @NotBlank(message = "Office address can't be empty")
    @Size(min = 2, max = 200, message = "Office address string length limits exceeded")
    private String officeAddress;

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;
}
