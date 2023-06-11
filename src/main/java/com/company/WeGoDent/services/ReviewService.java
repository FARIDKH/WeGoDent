package com.company.WeGoDent.services;


import com.company.WeGoDent.forms.ReviewForm;
import com.company.WeGoDent.models.Review;
import com.company.WeGoDent.repositories.AppointmentRepository;
import com.company.WeGoDent.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {


    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Review createReview(ReviewForm reviewForm) {

        Review review = new Review();
        review.setAppointment(
                appointmentRepository.findById(reviewForm.appointment_id).get()
        );
        review.setRating(reviewForm.rating);
        review.setComment(reviewForm.comment);

        reviewRepository.save(review);

        return review;
    }

    public Double getDoctorsRating(Long doctorId) {

        return reviewRepository.getAverageRatingOfDoctor(doctorId);
    }


}
