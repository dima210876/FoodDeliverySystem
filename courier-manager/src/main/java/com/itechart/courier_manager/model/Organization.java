package com.itechart.courier_manager.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "CourierOrganization")
@Table(
        name = "courier_organizations",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_courier_organizations_account_number",
                        columnNames = "account_number"
                )
        }
)
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
            nullable = false,
            columnDefinition = "VARCHAR(30)"
    )
    private String name;

    @Column(
            name = "account_number",
            nullable = false,
            columnDefinition = "VARCHAR(200)"
    )
    private String accountNumber;

    @Column(
            name = "phone_number",
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private String phoneNumber;

    @Column(
            name = "office_address",
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private String officeAddress;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "courierOrganization")
    private List<CourierInOrganization> couriersInOrganization = new ArrayList<>();
}

