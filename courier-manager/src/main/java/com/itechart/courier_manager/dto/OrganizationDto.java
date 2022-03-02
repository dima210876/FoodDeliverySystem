package com.itechart.courier_manager.dto;

import com.itechart.courier_manager.model.Courier;
import lombok.Data;

import java.util.Set;

@Data
public class OrganizationDto {
    private String name;
    private String accountNumber;
    private String phoneNumber;
    private String officeAddress;
    private Set<Courier> couriers;
}
