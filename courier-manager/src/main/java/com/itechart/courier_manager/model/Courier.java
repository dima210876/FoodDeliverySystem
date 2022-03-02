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
@Entity(name = "Courier")
@Table(name = "couriers")
public class Courier {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "role")
    @NotNull(message = "Role is required")
    @NotBlank(message = "Role can't be empty")
    @Size(max = 100, message = "Role string length limits exceeded")
    private String role;

    @Column(name = "first_name")
    @NotNull(message = "First name is required")
    @NotBlank(message = "First name can't be empty")
    @Size(max = 100, message = "First name string length limits exceeded")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name can't be empty")
    @Size(max = 100, message = "Last name string length limits exceeded")
    private String lastName;

    @Column(name = "phone_number")
    @NotNull(message = "Phone number is required")
    @NotBlank(message = "Phone number can't be empty")
    @Size(max = 100, message = "Phone number string length limits exceeded")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(
            name = "FK_organizations_id",
            foreignKey = @ForeignKey(
                    name = "delivery_organization_id"
            )
    )
    @NotNull(message = "Organization is required")
    @JsonBackReference
    private Organization organization;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "couriers"
    )
    @JsonManagedReference
    private Set<CourierOrder> orders;
}
