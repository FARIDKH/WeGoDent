package com.company.WeGoDent.controllers;


import com.company.WeGoDent.forms.ReviewForm;
import com.company.WeGoDent.entity.Review;
import com.company.WeGoDent.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Review> createReviewForAppointment(@RequestBody ReviewForm reviewForm){

        return new ResponseEntity<>(
                reviewService.createReview(reviewForm),
                HttpStatus.OK
        );

    }

}
