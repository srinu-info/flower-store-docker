package com.web.store.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;

import com.web.store.review.dto.FeedbackRequest;
import com.web.store.review.dto.FeedbackResponse;
import com.web.store.review.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService service;
    
    @PostMapping
    public String submitReview(
            Authentication authentication,
            @RequestBody
            FeedbackRequest request) {

        return service.submitReview(
                authentication.getName(),
                request);
    }
    @GetMapping
    public List<FeedbackResponse>
    getAllReviews() {

        return service.getAllReviews();
    }
    
}