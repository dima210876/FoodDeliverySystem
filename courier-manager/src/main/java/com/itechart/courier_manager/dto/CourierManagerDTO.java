package com.itechart.courier_manager.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CourierManagerDTO {
    @NotNull(message = "Restaurant address is required")
    @NotBlank(message = "Restaurant address can't be empty")
    @Size(min = 2, max = 100, message = "Email string length limits exceeded")
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password can't be empty")
    @Size(min = 6, max = 200, message = "Password string length limits exceeded")
    private String password;

    @NotNull(message = "First name is required")
    @NotBlank(message = "First name can't be empty")
    @Size(min = 2, max = 100, message = "Email string length limits exceeded")
    private String firstName;

    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name can't be empty")
    @Size(min = 2, max = 100, message = "Last name string length limits exceeded")
    private String lastName;

    @Size(min = 2, max = 50, message = "Phone number string length limits exceeded")
    private String phoneNumber;

    @NotNull(message = "Organization name is required")
    @NotBlank(message = "Organization name can't be empty")
    @Size(min = 2, max = 100, message = "Organization name string length limits exceeded")
    private String organizationName;
}
