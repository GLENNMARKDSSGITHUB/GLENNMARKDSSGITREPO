/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service;

import com.dss.dto.reviews.ReviewsDTO;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.repository.reviews.ReviewsRepository;
import com.dss.transformer.ReviewsTransformer;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is a service implementation for DSS Account Registration
 * @see #addReview(ReviewsDTO)
 * @see #displayReviews()
// * @see #updateReview(ReviewsDTO)
 */

@Service
public class ReviewsServiceImpl implements ReviewsService{
    private static final Logger logger = LoggerFactory.getLogger(ReviewsServiceImpl.class);
    private final ReviewsTransformer transformer = new ReviewsTransformer();
    private final DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
    private final DssCommonMethods commonMethods = new DssCommonMethods();

    @Autowired
    private DssMovieRepository dssMovieRepository;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public DssCommonMessageDetails addReview(ReviewsDTO reviewsDto) {
        logger.debug("ReviewsServiceImpl | addReview | Start ");
        try{
            String movieId = reviewsDto.getMovieId();
            List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(movieId);
            if(!movieList.isEmpty()){
                reviewsDto.setReviewId(commonMethods.reviewIdGeneration(reviewsRepository.maxReviewId()));
                reviewsRepository.save(transformer.transformToReviews(reviewsDto, movieList.get(0)));
                commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_001_ADD_REVIEW));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_002_MOVIE_NOT_EXIST));
                commonMsgDtl.setSuccess(false);
            }
        }catch(Exception ex){
            commonMsgDtl.setSuccess(false);
            logger.error("ReviewsServiceImpl | addReview | Error : {}", ex.getMessage());
        }finally {
            logger.debug("ReviewsServiceImpl | addReview | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails displayReviews() {
        logger.debug("ReviewsServiceImpl | displayReviews | Start ");
        try{
            List<Reviews> reviewsList = reviewsRepository.findAll();
            if(!reviewsList.isEmpty()){
                commonMsgDtl.setObjList(transformer.transformToReviews((reviewsList)));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
                commonMsgDtl.setSuccess(false);
            }
        }catch(Exception ex){
            commonMsgDtl.setSuccess(false);
            logger.error("ReviewsServiceImpl | displayReviews | Error : {}", ex.getMessage());
        }finally {
            logger.debug("ReviewsServiceImpl | displayReviews | End ");
        }
        return commonMsgDtl;
    }
}
