package com.itechart.courier_manager.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "delivery_organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(max = 30, message = "Name string length limits exceeded")
    private String name;

    @Size(max = 200, message = "Account number string length limits exceeded")
    private String accountNumber;

    @Size(max = 50, message = "Phone number string length limits exceeded")
    private String phoneNumber;

    @NotNull(message = "Office address is required")
    @NotBlank(message = "Office address can't be empty")
    @Size(max = 200, message = "Office address string length limits exceeded")
    private String officeAddress;

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;


    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "organization"
    )
    @JsonBackReference
    private Set<Courier> couriers;
}