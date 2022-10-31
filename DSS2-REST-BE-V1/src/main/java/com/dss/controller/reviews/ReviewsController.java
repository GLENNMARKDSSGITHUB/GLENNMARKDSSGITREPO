package com.dss.controller.reviews;

import com.dss.dto.reviews.ReviewsDTO;
import com.dss.entity.reviews.Reviews;
import com.dss.service.reviews.ReviewsService;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/reviews")
public class ReviewsController {
    private static final Logger logger = LoggerFactory.getLogger(ReviewsController.class);
    private final DssCommonUtility commonUtil = new DssCommonUtility();

    @Autowired
    private ReviewsService reviewsService;

    @PostMapping("/add-review.do")
    public String addReview(@RequestBody ReviewsDTO reviewsDto){
        logger.debug("ReviewsController | addReview | Start");
        logger.debug("ReviewsController | addReview | reviewsDto : " + reviewsDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = reviewsService.addReview(reviewsDto);
            logger.debug(commonMsgDtl.getContent());
        }catch(Exception ex){
            logger.error("ReviewsController | addReview | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ReviewsController | addReview | End");
        }
        return commonMsgDtl.getContent();
    }

    @GetMapping("/display-reviews.do")
    public String displayReviews(){
        logger.debug("ReviewsController | displayReviews | Start");
        DssCommonMessageDetails commonMsgDtl = reviewsService.displayReviews();
        try{
            if(commonMsgDtl.isSuccess()){
                List<Reviews> reviewsList = (List<Reviews>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(reviewsList));
                logger.debug("ReviewsController | displayReviews | reviews : \n" + commonUtil.gsonToJsonString(reviewsList));
            }else{
                logger.error("ReviewsController | displayReviews | reviews : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            logger.error("ReviewsController | displayReviews | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ReviewsController | displayReviews  | End");
        }
        return commonMsgDtl.getContent();
    }

    @GetMapping("/search-review.do")
    public String searchReview(HttpServletRequest request){
        logger.debug("ReviewsController | searchReview | Start");
        String movieTitle = request.getParameter("movieTitle");
        DssCommonMessageDetails commonMsgDtl = reviewsService.searchReviewByMovieTitle(movieTitle);
        try{
            if(commonMsgDtl.isSuccess()){
                List<Reviews> reviewsList = (List<Reviews>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(reviewsList));
                logger.debug("ReviewsController | searchReview | review : \n" + commonUtil.gsonToJsonString(reviewsList));
            }else{
                logger.error("ReviewsController | searchReview | review : " + commonMsgDtl.getContent());
            }
        }catch(Exception ex){
            logger.error("ReviewsController | searchReview | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ReviewsController | searchReview | End");
        }
        return commonMsgDtl.getContent();
    }

    @PutMapping("/update-review.do")
    public String updateReview(@RequestBody ReviewsDTO reviewsDto){
        logger.debug("ReviewsController | updateReview | Start");
        logger.debug("ReviewsController | updateReview | reviewsDto : " + reviewsDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = reviewsService.updateReview(reviewsDto);
            logger.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            logger.error("ReviewsController | updateReview | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("ReviewsController | updateReview | End");
        }
        return commonMsgDtl.getContent();
    }
}
