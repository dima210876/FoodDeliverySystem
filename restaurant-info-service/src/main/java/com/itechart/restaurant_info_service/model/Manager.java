package com.itechart.restaurant_info_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "managers")
public class Manager {

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

    @Size(min = 0, max = 50, message = "Phone number string length limits exceeded")
    private String phoneNumber;

    @NotNull(message = "Role is required")
    @NotBlank(message = "Role can't be empty")
    @Size(min = 2, max = 50, message = "Role string length limits exceeded")
    private String role;

    @OneToOne(mappedBy = "manager")
    @JsonBackReference
    private Restaurant restaurant;
}
