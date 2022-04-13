package com.itechart.restaurant_info_service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class FeedbackDTO {
    @NotNull(message = "User id is required")
    private Long userId;

    @NotNull(message = "Rating is required")
    @Size(min = 0, max = 10, message = "Rating limits exceeded")
    private Integer rating;

    @Size(min = 2, max = 200, message = "Comment string length limits exceeded")
    private String comment;

    @NotNull(message = "Restaurant id is required")
    private Long restaurantId;
}
