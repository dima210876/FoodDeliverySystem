package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticsDTO {
    private Integer numberOfDaysForChart = 30;
    private List<Integer> ordersNumberPerDay;
    private List<Double> incomePerDay;
}
