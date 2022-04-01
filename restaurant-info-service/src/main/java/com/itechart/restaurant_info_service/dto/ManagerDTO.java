package com.itechart.restaurant_info_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDTO {
    @NotNull(message = "Restaurant address is required")
    @NotBlank(message = "Restaurant address can't be empty")
    @Size(min = 2, max = 100, message = "Email string length limits exceeded")
    private String email;
}
