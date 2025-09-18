package com.spookzie.jobapp.review.impl;

import com.spookzie.jobapp.company.Company;
import com.spookzie.jobapp.company.CompanyService;
import com.spookzie.jobapp.review.Review;
import com.spookzie.jobapp.review.ReviewRepository;
import com.spookzie.jobapp.review.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService
{
    private final ReviewRepository reviewRepo;
    private final CompanyService companyService;


    /*  GET */
    @Override
    public List<Review> findAll(Long companyId)
    {
        return this.reviewRepo.findByCompanyId(companyId);
    }

    @Override
    public Review findById(Long companyId, Long reviewId)
    {
        List<Review> reviews = this.reviewRepo.findByCompanyId(companyId);

        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }


    /*  POST    */
    @Override
    public Review addReview(Long companyId, Review review)
    {
        Company company = this.companyService.findById(companyId);
        if(company != null)
        {
            review.setCompany(company);
            return this.reviewRepo.save(review);
        }

        return null;
    }


    /*  PUT */
    @Override
    public Review fullUpdateReview(Long companyId, Long reviewId, Review review)
    {
        Review currReview = this.reviewRepo.findByCompanyId(companyId)
                .stream()
                .filter(r -> r.getId().equals(reviewId))
                .findFirst()
                .orElse(null);

        if(currReview != null)
        {
            currReview.setTitle(review.getTitle());
            currReview.setDescription(review.getDescription());
            currReview.setRating(review.getRating());

            this.reviewRepo.save(currReview);
            return currReview;
        }

        return null;
    }


    /*  DELETE  */
    @Override
    @Transactional
    public boolean deleteReview(Long companyId, Long reviewId)
    {
        if(this.companyService.findById(companyId) != null)
        {
            Review review = this.reviewRepo.findById(reviewId).orElse(null);

            if(review != null && review.getCompany().getId().equals(companyId))
            {
                Company company = review.getCompany();
                company.getReviews().remove(review);
                this.companyService.updateCompany(companyId, company);  // Remove review from company
                this.reviewRepo.deleteById(reviewId);   // Remove review from ReviewDB

                return true;
            }
        }

        return false;

    }
}