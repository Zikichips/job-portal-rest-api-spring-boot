package com.example.jobapp.review.impl;

import com.example.jobapp.company.Company;
import com.example.jobapp.company.CompanyService;
import com.example.jobapp.review.Review;
import com.example.jobapp.review.ReviewRepository;
import com.example.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> findAll(Long company_id) {
        return reviewRepository.findByCompanyId(company_id);
    }

    @Override
    public boolean addReview(Long company_id, Review review) {
        Company company = companyService.getCompanyById(company_id);
        if(company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review findById(Long company_id, Long review_id) {
        Company company = companyService.getCompanyById(company_id);
        if(company != null) {
            return company.getReviews().stream()
                    .filter(review -> review.getId().equals(review_id))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        Company theCompany = companyService.getCompanyById(companyId);
        if(theCompany != null) {
            review.setCompany(theCompany);
            review.setId(reviewId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Review review = this.findById(companyId, reviewId);
        if(review != null) {
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}
