/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.controller.reviews;

import com.dss.dto.reviews.ReviewsDTO;
import com.dss.entity.reviews.Reviews;
import com.dss.service.reviews.ReviewsService;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This class is a controller layer for DSS Reviews
 */

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/reviews")
@Slf4j
public class ReviewsController {

    private final DssCommonUtility commonUtil = new DssCommonUtility();

    @Autowired
    private ReviewsService reviewsService;

    /** Returns a String value if the admin user successfully adds the movie review or not.
     * @param reviewsDto reviewsDto
     * @return String
     * @see #addReview(ReviewsDTO)
     */
    @PostMapping("/add-review.do")
    public String addReview(@RequestBody ReviewsDTO reviewsDto){
        log.debug("ReviewsController | addReview | Start");
        log.debug("ReviewsController | addReview | reviewsDto : " + reviewsDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = reviewsService.addReview(reviewsDto);
            log.debug(commonMsgDtl.getContent());
        }catch(Exception ex){
            log.error("ReviewsController | addReview | Error msg : " + ex.getMessage());
        }finally{
            log.debug("ReviewsController | addReview | End");
        }
        return commonMsgDtl.getContent();
    }

    /** Returns a list of Reviews
     * @return List<Reviews>
     * @see #displayReviews()
     */
    @GetMapping("/display-reviews.do")
    public List<Reviews> displayReviews(){
        log.debug("ReviewsController | displayReviews | Start");
        List<Reviews> reviewsList = null;
        try{
            DssCommonMessageDetails commonMsgDtl = reviewsService.displayReviews();
            if(commonMsgDtl.isSuccess()){
                reviewsList = (List<Reviews>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(reviewsList));
                log.debug("ReviewsController | displayReviews | reviews : \n" + commonUtil.gsonToJsonString(reviewsList));
            }else{
                log.error("ReviewsController | displayReviews | reviews : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            log.error("ReviewsController | displayReviews | Error msg : " + ex.getMessage());
        }finally{
            log.debug("ReviewsController | displayReviews  | End");
        }
        return reviewsList;
    }

    /** Returns a specific movie review in a form of List<Reviews>
     * @param request HttpServletRequest
     * @return List<Reviews>
     * @see #searchReview(HttpServletRequest request)
     */
    @GetMapping("/search-review.do")
    public List<Reviews> searchReview(HttpServletRequest request){
        log.debug("ReviewsController | searchReview | Start");
        List<Reviews> reviewsList = null;
        try{
            String movieTitle = request.getParameter("movieTitle");
            DssCommonMessageDetails commonMsgDtl = reviewsService.searchReviewByMovieTitle(movieTitle);
            if(commonMsgDtl.isSuccess()){
                reviewsList = (List<Reviews>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(reviewsList));
                log.debug("ReviewsController | searchReview | review : \n" + commonUtil.gsonToJsonString(reviewsList));
            }else{
                log.error("ReviewsController | searchReview | review : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            log.error("ReviewsController | searchReview | Error msg : " + ex.getMessage());
        }finally{
            log.debug("ReviewsController | searchReview | End");
        }
        return reviewsList;
    }

    /** Returns a String value if the admin user successfully updates the movie review or not.
     * @param reviewsDto reviewsDto
     * @return String
     * @see #updateReview(ReviewsDTO)
     */
    @PutMapping("/update-review.do")
    public String updateReview(@RequestBody ReviewsDTO reviewsDto){
        log.debug("ReviewsController | updateReview | Start");
        log.debug("ReviewsController | updateReview | reviewsDto : " + reviewsDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = reviewsService.updateReview(reviewsDto);
            log.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            log.error("ReviewsController | updateReview | Error msg : " + ex.getMessage());
        }finally{
            log.debug("ReviewsController | updateReview | End");
        }
        return commonMsgDtl.getContent();
    }
}
