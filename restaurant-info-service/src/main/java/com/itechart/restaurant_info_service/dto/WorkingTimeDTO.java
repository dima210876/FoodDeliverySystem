package com.itechart.restaurant_info_service.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;

@Data
public class WorkingTimeDTO {

    @NotNull(message = "Opening time is required")
    @Range(min = 0, max = 1440, message = "Day of week limits exceeded")
    private Integer openingTimeInMinutes;

    @NotNull(message = "Closing time is required")
    @Range(min = 0, max = 1440, message = "Day of week limits exceeded")
    private Integer closingTimeInMinutes;

    @NotNull(message = "Day of week is required")
    @Range(min = 0, max = 7, message = "Day of week limits exceeded")
    private Integer dayOfWeek;
}

