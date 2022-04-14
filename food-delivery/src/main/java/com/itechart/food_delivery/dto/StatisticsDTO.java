package com.itechart.food_delivery.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticsDTO {
    private Integer numberOfDaysForChart = 30;
    private List<Integer> ordersNumberPerDay;
    private List<Double> incomePerDay;
}
