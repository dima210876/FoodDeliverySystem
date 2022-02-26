package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import java.sql.Time;

@Data
public class WorkingTimeDTO {
    private Time openingTime;
    private Time closingTime;
    private int dayOfWeek;
}

