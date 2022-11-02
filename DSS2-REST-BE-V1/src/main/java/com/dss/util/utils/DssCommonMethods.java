/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.util.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a DssCommonMethods Class.
 */

public class DssCommonMethods {
    private static final Logger logger = LoggerFactory.getLogger(DssCommonMethods.class);

    private final DssCommonUtility util = new DssCommonUtility();

    /** This method generates a new user ID which is based on the MAX userId in the DSS_USERS table.
     *  Returns a String value of maxUserId
     * @param maxUserId maxUserId
     * @return String value
     * @see #userIdGeneration(String)
     */
    public String userIdGeneration(String maxUserId){
        String userId;
        logger.debug("DssCommonMethods | userIdGeneration | maxUserId : " + maxUserId);
        int count = 0;
        if(!util.isNullOrEmpty(maxUserId)){
            count = Integer.parseInt(maxUserId.replaceAll("[^0-9 ]", ""));
        }

        count = count + 1;
        int countDigit = (int)Math.floor(Math.log10(count) + 1);
        if(countDigit == 1){
            userId = String.format(CommonStringUtility.US_ID_000X, count);
        } else if(countDigit == 2){
            userId = String.format(CommonStringUtility.US_ID_00XX, count);
        } else if(countDigit == 3){
            userId = String.format(CommonStringUtility.US_ID_0XXX, count);
        } else{
            userId = String.format(CommonStringUtility.US_ID_XXXX, count);
        }
        logger.debug("DssCommonMethods | dssIdGeneration | Generated userId : " + userId);
        return userId;
    }


    /** This method generates a new movie ID which is based on the MAX userId in the DSS_MOVIE table.
     *  Returns a String value of maxUserId
     * @param maxDssId maxDssId
     * @return String value
     * @see #dssIdGeneration(String)
     */
    public String dssIdGeneration(String maxDssId){
        String dssId;
        int count = 0;
        if(!util.isNullOrEmpty(maxDssId)){
            count = Integer.parseInt(maxDssId.replaceAll("[^0-9 ]", ""));
        }

        count = count + 1;
        int countDigit = (int)Math.floor(Math.log10(count) + 1);
        if(countDigit == 1){
            dssId = String.format(CommonStringUtility.DSS_ID_000X, count);
        } else if(countDigit == 2){
            dssId = String.format(CommonStringUtility.DSS_ID_00XX, count);
        } else if(countDigit == 3){
            dssId = String.format(CommonStringUtility.DSS_ID_0XXX, count);
        } else{
            dssId = String.format(CommonStringUtility.DSS_ID_XXXX, count);
        }
        logger.debug("DssCommonMethods | dssIdGeneration | Generated dssId : " + dssId);
        return dssId;
    }


    /** This method generates a new actor ID which is based on the MAX userId in the DSS_ACTORS table.
     *  Returns a String value of maxUserId
     * @param maxActorId maxActorId
     * @return String value
     * @see #actorIdGeneration(String)
     */
    public String actorIdGeneration(String maxActorId){
        String actorId;
        int count = 0;
        if(!util.isNullOrEmpty(maxActorId)){
            count = Integer.parseInt(maxActorId.replaceAll("[^0-9 ]", ""));
        }

        int countDigit = (int)Math.floor(Math.log10(count) + 1);
        if(countDigit == 1){
            actorId = String.format(CommonStringUtility.ACT_ID_000X, count);
        } else if(countDigit == 2){
            actorId = String.format(CommonStringUtility.ACT_ID_00XX, count);
        } else if(countDigit == 3){
            actorId = String.format(CommonStringUtility.ACT_ID_0XXX, count);
        } else{
            actorId = String.format(CommonStringUtility.ACT_ID_XXXX, count);
        }
        logger.debug("DssCommonMethods | actorIdGeneration | Generated actorId : " + actorId);
        return actorId;
    }

    /** This method generates a new review ID which is based on the MAX userId in the DSS_REVIEWS table.
     *  Returns a String value of maxUserId
     * @param maxReviewId maxReviewId
     * @return String value
     * @see #reviewIdGeneration(String)
     */
    public String reviewIdGeneration(String maxReviewId){
        String reviewId;
        int count = 0;
        if(util.isNullOrEmpty(maxReviewId)){
            count = Integer.parseInt(maxReviewId.replaceAll("[^0-9 ]", ""));
        }

        count = count + 1;
        int countDigit = (int)Math.floor(Math.log10(count) + 1);
        if(countDigit == 1){
            reviewId = String.format(CommonStringUtility.REV_ID_000X, count);
        } else if(countDigit == 2){
            reviewId = String.format(CommonStringUtility.REV_ID_00XX, count);
        } else if(countDigit == 3){
            reviewId = String.format(CommonStringUtility.REV_ID_0XXX, count);
        } else{
            reviewId = String.format(CommonStringUtility.REV_ID_XXXX, count);
        }
        logger.debug("DssCommonMethods | actorIdGeneration | Generated reviewId : " + reviewId);
        return reviewId;
    }
}
