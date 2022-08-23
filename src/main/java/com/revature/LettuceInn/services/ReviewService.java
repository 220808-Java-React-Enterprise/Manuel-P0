package com.revature.LettuceInn.services;

import com.revature.LettuceInn.daos.ReviewDAO;
import com.revature.LettuceInn.models.Review;

import java.util.List;

public class ReviewService {
    private final ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public void saveReview(Review review) {
        reviewDAO.save(review);
    }

    public List<Review> getAllReviewsByRestaurantId(String id) {
        return reviewDAO.getAllByRestaurantId(id);
    }
}
