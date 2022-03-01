package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.FeedbackDTO;
import com.itechart.restaurant_info_service.model.Feedback;
import com.itechart.restaurant_info_service.model.Restaurant;
import com.itechart.restaurant_info_service.repository.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@AllArgsConstructor
@Validated
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public void addFeedback(@Valid FeedbackDTO feedbackDTO){
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
