package com.itechart.food_delivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.TimestampType;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "courier_id")
    private Long courierId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "food_order_id")
    private Long foodOrderId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "order_address")
    private String orderAddress;

    @Column(name = "order_price")
    private double orderPrice;

    @Column(name = "shipping_price")
    private double shippingPrice;

    @Column(name = "discount")
    private int discount;

    @Column(name = "creation_time")
    private TimestampType creationTime;

    @Column(name = "delivery_time")
    private TimestampType deliveryTime;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    public Order(Long id, Long courierId, Long customerId,
                 Long foodOrderId, String orderStatus,
                 String orderAddress, double orderPrice,
                 double shippingPrice, int discount,
                 TimestampType creationTime, TimestampType deliveryTime,
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
