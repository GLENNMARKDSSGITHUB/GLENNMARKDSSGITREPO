package com.dss.util.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DssCommonMethods {
    private static final Logger logger = LoggerFactory.getLogger(DssCommonMethods.class);

    public String userIdGeneration(String maxUserId){
        String userId;
        int count = Integer.parseInt(maxUserId.replaceAll("[^0-9 ]", ""));
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

    public String dssIdGeneration(String maxDssId){
        String dssId;
        int count = Integer.parseInt(maxDssId.replaceAll("[^0-9 ]", ""));
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

    public String actorIdGeneration(String maxActorId){
        String actorId;
        int count = Integer.parseInt(maxActorId.replaceAll("[^0-9 ]", ""));
        count = count + 1;
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

    public String reviewIdGeneration(String maxReviewId){
        String reviewId;
        int count = Integer.parseInt(maxReviewId.replaceAll("[^0-9 ]", ""));
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
