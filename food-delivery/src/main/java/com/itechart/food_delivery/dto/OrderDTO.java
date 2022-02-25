package com.itechart.food_delivery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private Long foodOrderId;
    private String orderAddress;
    private double orderPrice;
    private double shippingOrder;
    private int discount;

    public OrderDTO(Long foodOrderId, String orderAddress, double orderPrice, double shippingOrder, int discount) {
        this.foodOrderId = foodOrderId;
        this.orderAddress = orderAddress;
        this.orderPrice = orderPrice;
        this.shippingOrder = shippingOrder;
        this.discount = discount;
    }
}
