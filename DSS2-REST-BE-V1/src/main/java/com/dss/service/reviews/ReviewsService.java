package com.dss.service.reviews;

import com.dss.dto.reviews.ReviewsDTO;
import com.dss.util.utils.DssCommonMessageDetails;

public interface ReviewsService {

    DssCommonMessageDetails addReview(ReviewsDTO reviewsDto);
    DssCommonMessageDetails displayReviews();
    DssCommonMessageDetails searchReviewByMovieTitle(String movieTitle);
    DssCommonMessageDetails updateReview(ReviewsDTO reviewsDto);
}
