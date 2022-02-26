package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.FeedbackDTO;
import com.itechart.restaurant_info_service.model.Feedback;
import com.itechart.restaurant_info_service.model.Restaurant;
import com.itechart.restaurant_info_service.repository.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public void addFeedback(FeedbackDTO feedbackDTO){
        Feedback feedback = Feedback.builder()
                .userId(feedbackDTO.getUserId())
                .rating(feedbackDTO.getRating())
                .comment(feedbackDTO.getComment())
                .restaurant(Restaurant.builder()
                        .id(feedbackDTO.getRestaurantId())
                        .build())
                .build();
        //TODO: check rating diapason
        feedbackRepository.save(feedback);
    }
}
