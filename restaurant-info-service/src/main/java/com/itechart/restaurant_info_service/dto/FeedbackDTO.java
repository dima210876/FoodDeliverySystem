package com.itechart.restaurant_info_service.dto;

import lombok.Data;

@Data
public class FeedbackDTO {
    private Long userId;
    private int rating;
    private String comment;
    private Long restaurantId;
}
