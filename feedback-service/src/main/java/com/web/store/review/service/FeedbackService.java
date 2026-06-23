package com.web.store.review.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.store.review.dto.FeedbackRequest;
import com.web.store.review.dto.FeedbackResponse;
import com.web.store.review.entity.Feedback;
import com.web.store.review.repository.FeedbackRepository;


@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    /**
     * Submit feedback
     */
    public String submitReview(String email,
                               FeedbackRequest request) {

        Feedback feedback = new Feedback();

        feedback.setEmail(email);
        feedback.setRating(request.getRating());
        feedback.setReviewMessage(request.getReviewMessage());
        feedback.setCreatedAt(LocalDateTime.now());

        feedbackRepository.save(feedback);

        return "Review submitted successfully";
    }

    /**
     * Get all reviews
     */
    public List<FeedbackResponse> getAllReviews() {

        List<Feedback> feedbackList =
                feedbackRepository.findAllByOrderByCreatedAtDesc();

        return feedbackList.stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Get review by id
     */
    public FeedbackResponse getReviewById(Long id) {

        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Review not found"));

        return mapToResponse(feedback);
    }

    /**
     * Delete review
     * (optional admin functionality)
     */
    public String deleteReview(Long id) {

        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Review not found"));

        feedbackRepository.delete(feedback);

        return "Review deleted successfully";
    }

    /**
     * Entity -> DTO Mapper
     */
    private FeedbackResponse mapToResponse(
            Feedback feedback) {

        FeedbackResponse response =
                new FeedbackResponse();

        response.setId(feedback.getId());
        response.setEmail(feedback.getEmail());
        response.setRating(feedback.getRating());
        response.setReviewMessage(feedback.getReviewMessage());
        response.setCreatedAt(
                feedback.getCreatedAt());

        return response;
    }
}
