package com.spookzie.jobapp.review;

import java.util.List;


public interface ReviewService
{
    List<Review> findAll(Long companyId);

    Review findById(Long companyId, Long reviewId);

    Review addReview(Long companyId, Review review);

    Review fullUpdateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}