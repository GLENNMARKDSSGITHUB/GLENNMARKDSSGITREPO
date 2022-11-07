/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.reviews;

import com.dss.dto.reviews.ReviewsDTO;
import com.dss.util.utils.DssCommonMessageDetails;

/**
 * This is a service layer for DSS Reviews
 */

public interface ReviewsService {

    /** Returns a String value if the admin user successfully adds the movie review or not.
     * @param reviewsDto reviewsDto
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #addReview(ReviewsDTO)
     */
    DssCommonMessageDetails addReview(ReviewsDTO reviewsDto);

    /** Returns a list of Reviews
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #displayReviews()
     */
    DssCommonMessageDetails displayReviews();
}
