package com.dss.service.reviews;

import com.dss.dto.reviews.ReviewsDTO;
import com.dss.util.utils.DssCommonMessageDetails;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

public interface ReviewsService {

    DssCommonMessageDetails addReview(ReviewsDTO reviewsDto);
    DssCommonMessageDetails displayReviews();
    DssCommonMessageDetails searchReviewByMovieTitle(String movieTitle);
    DssCommonMessageDetails updateReview(ReviewsDTO reviewsDto);
}
