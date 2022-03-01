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
@Entity(name = "CourierOrder")
@Table(name = "courier_orders")
public class CourierOrder {

    @Id
    @SequenceGenerator(
            name = "courier_orders_id_seq",
            sequenceName = "courier_orders_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "courier_orders_id_seq"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "сourier_id",
            nullable = false
    )
    private Long courierId;
    @Column(
            name = "order_id",
            nullable = false
    )
    private Long orderId;

    @Column(
            name = "delivery_status",
            nullable = false
    )
    private String deliveryStatus;

    @Column(
            name = "delivery_method",
            nullable = false
    )
    private String deliveryMethod;
}

