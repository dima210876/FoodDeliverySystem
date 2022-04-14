package com.itechart.restaurant_info_service.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;

@Data
public class WorkingTimeDTO {

    @NotNull(message = "Opening time is required")
    @Size(max = 100, message = "Time limits exceeded")
    private String openingTime;

    @NotNull(message = "Closing time is required")
    @Size(max = 100, message = "Time limits exceeded")
    private String closingTime;

    @NotNull(message = "Day of week is required")
    @Range(min = 0, max = 7, message = "Day of week limits exceeded")
    private Integer dayOfWeek;
}

