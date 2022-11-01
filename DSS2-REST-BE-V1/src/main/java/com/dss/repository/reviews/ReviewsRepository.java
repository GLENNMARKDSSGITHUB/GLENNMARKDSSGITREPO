package com.dss.repository.reviews;

import com.dss.entity.reviews.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, String> {

    @Query(value = "SELECT * FROM DSS_REVIEWS WHERE REVIEW_ID = :reviewId", nativeQuery = true)
    List<Reviews> findReviewByReviewId(@Param("reviewId") String reviewId);

    @Query(value = "SELECT MAX(REVIEW_ID) FROM DSS_REVIEWS", nativeQuery = true)
    String maxReviewId();
}
