package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;

@Data
public class WorkingTimeDTO {

    @NotNull(message = "Opening time is required")
    private Time openingTime;

    @NotNull(message = "Closing time is required")
    private Time closingTime;

    @NotNull(message = "Day of week is required")
    @Size(min = 1, max = 7, message = "Day of week limits exceeded")
    private int dayOfWeek;
}

