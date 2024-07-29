package com.example.jobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{company_id}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> findAll(@PathVariable Long company_id) {
        return new ResponseEntity<>(reviewService.findAll(company_id), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long company_id, @RequestBody Review review) {
        boolean reviewAdded = reviewService.addReview(company_id, review);
        if(reviewAdded) {
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Could not be added", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/reviews/{review_id}")
    public ResponseEntity<Review> findReviewById(@PathVariable Long company_id, @PathVariable Long review_id){
        Review review = reviewService.findById(company_id, review_id);
        if(review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{review_id}")
    public ResponseEntity<String> updateReview(@PathVariable Long company_id, @PathVariable Long review_id, @RequestBody Review review) {
        boolean updateReview = reviewService.updateReview(company_id,review_id,review);
        if(updateReview) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review could not be updated", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/reviews/{review_id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long company_id, @PathVariable Long review_id) {
        boolean deleteReview = reviewService.deleteReview(company_id,review_id);
        if(deleteReview) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review could not be deleted", HttpStatus.BAD_REQUEST);
    }
}
