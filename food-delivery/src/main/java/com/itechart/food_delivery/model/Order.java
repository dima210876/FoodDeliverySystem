package com.itechart.food_delivery.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(name = "courier_id")
    @NotNull
    private Long courierId;

    @Column(name = "customer_id")
    @NotNull
    private Long customerId;

    @Column(name = "food_order_id")
    @NotNull
    private Long foodOrderId;

    @Column(name = "order_status")
    @Length(max=20)
    @NotNull
    private String orderStatus;

    @Column(name = "order_address")
    @Length(max=200)
    @NotNull
    private String orderAddress;

    @Column(name = "order_price")
    @NotNull
    private double orderPrice;

    @Column(name = "shipping_price")
    @NotNull
    private double shippingPrice;

    @Column(name = "discount")
    @NotNull
    private int discount;

    @Column(name = "creation_time")
    @NotNull
    private LocalDateTime creationTime;

    @Column(name = "delivery_time")
    @NotNull
    private LocalDateTime deliveryTime;

    @Column(name = "latitude")
    @NotNull
    private double latitude;

    @Column(name = "longitude")
    @NotNull
    private double longitude;

    public Order(Long id, Long courierId, Long customerId,
                 Long foodOrderId, String orderStatus,
                 String orderAddress, double orderPrice,
                 double shippingPrice, int discount,
                 LocalDateTime creationTime, LocalDateTime deliveryTime,
                 double latitude, double longitude) {
        this.id = id;
        this.courierId = courierId;
        this.customerId = customerId;
        this.foodOrderId = foodOrderId;
        this.orderStatus = orderStatus;
        this.orderAddress = orderAddress;
        this.orderPrice = orderPrice;
        this.shippingPrice = shippingPrice;
        this.discount = discount;
        this.creationTime = creationTime;
        this.deliveryTime = deliveryTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
