package com.example.jobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll(Long company_id);
    boolean addReview(Long company_id, Review review);
    Review findById(Long company_id, Long review_id);
    boolean updateReview(Long companyId, Long reviewId, Review review);
    boolean deleteReview(Long companyId, Long reviewId);
}
