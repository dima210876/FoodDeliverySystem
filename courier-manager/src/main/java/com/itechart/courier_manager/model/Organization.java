package com.itechart.courier_manager.model;
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

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "DeliveryOrganization")
@Table(name = "delivery_organizations")
public class Organization {
    @Id
    @SequenceGenerator(
            name = "courier_organizations_id_seq",
            sequenceName = "courier_organizations_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "courier_organizations_id_seq"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name can't be empty")
    @Size(max = 30, message = "Name string length limits exceeded")
    private String name;

    @Column(name = "account_number")
    @NotNull(message = "Account number is required")
    @NotBlank(message = "Account number can't be empty")
    @Size(max = 200, message = "Account number string length limits exceeded")
    private String accountNumber;

    @Column(name = "phone_number")
    @NotNull(message = "Phone number is required")
    @NotBlank(message = "Phone number can't be empty")
    @Size(max = 50, message = "Phone number string length limits exceeded")
    private String phoneNumber;

    @Column(name = "office_address")
    @NotNull(message = "Office address is required")
    @NotBlank(message = "Office address can't be empty")
    @Size(max = 50, message = "Office address string length limits exceeded")
    private String officeAddress;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "delivery_organizations"
    )
    @JsonManagedReference
    private Set<Courier> couriers;
}
