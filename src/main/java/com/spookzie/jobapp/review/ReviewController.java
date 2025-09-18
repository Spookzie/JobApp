package com.spookzie.jobapp.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/companies/{companyId}/reviews")
@RequiredArgsConstructor
public class ReviewController
{
    private final ReviewService reviewService;

    /*  GET */
    @GetMapping
    public ResponseEntity<List<Review>> findAll(@PathVariable Long companyId)
    {
        return new ResponseEntity<>(
                this.reviewService.findAll(companyId),
                HttpStatus.OK
        );
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> findById(@PathVariable Long companyId, @PathVariable Long reviewId)
    {
        Review review = this.reviewService.findById(companyId, reviewId);
        if(review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    /*  POST    */
    @PostMapping
    public ResponseEntity<Review> addReview(@PathVariable Long companyId, @RequestBody Review review)
    {
        Review addedReview = this.reviewService.addReview(companyId, review);
        if(review != null)
        {
            return new ResponseEntity<>(
                addedReview,
                HttpStatus.CREATED
            );
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    /*  PUT */
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> fullUpdateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review)
    {
        Review updatedReview = this.reviewService.fullUpdateReview(companyId, reviewId, review);
        if(updatedReview != null)
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    /*  DELETE  */
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId)
    {
        boolean isDeleted = this.reviewService.deleteReview(companyId, reviewId);

        if(isDeleted)
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>("Unable to delete review", HttpStatus.NOT_FOUND);
    }
}