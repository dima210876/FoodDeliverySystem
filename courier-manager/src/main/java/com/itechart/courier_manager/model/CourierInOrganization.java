package com.itechart.courier_manager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "courierOrganizationsRelationship")
@Table(name = "courier_organizations_couriers ")
public class CourierInOrganization {
    @Id
    @SequenceGenerator(
            name = "courier_organizations_couriers_id",
            sequenceName = "courier_organizations_couriers_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "courier_organizations_couriers_id"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "courier_organizations_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Organization courierOrganization;

    @Column(
            name = "сourier_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Long courierId;
}
