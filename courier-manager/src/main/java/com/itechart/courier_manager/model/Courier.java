package com.itechart.courier_manager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
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
    private String role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(
            name = "FK_organizations_id",
            foreignKey = @ForeignKey(
                    name = "delivery_organization_id"
            )
    )
    private Organization organization;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "couriers"
    )
    private Set<CourierOrder> orders = new HashSet<>();
}
