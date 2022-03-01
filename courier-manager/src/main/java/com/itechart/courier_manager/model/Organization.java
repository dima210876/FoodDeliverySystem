package com.itechart.courier_manager.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;
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

    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "account_number",
            nullable = false
    )
    private String accountNumber;

    @Column(
            name = "phone_number",
            nullable = false
    )
    private String phoneNumber;

    @Column(
            name = "office_address",
            nullable = false
    )
    private String officeAddress;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "delivery_organizations"
    )
    private Set<Courier> couriers = new HashSet<>();
}
