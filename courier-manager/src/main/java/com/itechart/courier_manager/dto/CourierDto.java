package com.itechart.courier_manager.dto;

import com.itechart.courier_manager.model.CourierOrder;
import com.itechart.courier_manager.model.Organization;
import lombok.Data;

import java.util.Set;

@Data
public class CourierDto {
    private String role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Organization organization;
    private Set<CourierOrder> orders;
}
