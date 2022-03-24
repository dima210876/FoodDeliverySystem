package com.itechart.food_delivery.dto;

import lombok.Data;

@Data
public class OrderDTO{
    private Long id;
    private Long foodOrderId;
    private String orderAddress;
    private double orderPrice;
    private double shippingOrder;
    private int discount;

    public OrderDTO(Long id, Long foodOrderId, String orderAddress, double orderPrice, double shippingOrder, int discount) {
        this.id = id;
        this.foodOrderId = foodOrderId;
        this.orderAddress = orderAddress;
        this.orderPrice = orderPrice;
        this.shippingOrder = shippingOrder;
        this.discount = discount;
    }
}
