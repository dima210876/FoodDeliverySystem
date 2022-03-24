package com.itechart.food_delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private Long userId;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email can't be empty")
    @Size(min = 2, max = 100, message = "Email string length limits exceeded")
    private String email;

    @NotNull(message = "First name is required")
    @NotBlank(message = "First name can't be empty")
    @Size(min = 2, max = 100, message = "First name string length limits exceeded")
    private String firstName;

    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name can't be empty")
    @Size(min = 2, max = 100, message = "Last name string length limits exceeded")
    private String lastName;

    @Size(min = 2, max = 50, message = "Phone number string length limits exceeded")
    private String phoneNumber;

    @NotNull(message = "Role is required")
    @NotBlank(message = "Role can't be empty")
    @Size(min = 2, max = 50, message = "Role string length limits exceeded")
    private String role;
}
