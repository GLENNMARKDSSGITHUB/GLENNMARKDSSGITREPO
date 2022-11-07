/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.util.utils;

/**
 * This is a DssCommonMethods Class.
 */

public class DssCommonMethods {

    private static final String REGEX_GET_NUMBERS = "[^0-9 ]";

    private final DssCommonUtility util = new DssCommonUtility();

    /** This method generates a new review ID which is based on the MAX userId in the DSS_REVIEWS table.
     *  Returns a String value of maxUserId
     * @param maxReviewId maxReviewId
     * @return String value
     * @see #reviewIdGeneration(String)
     */
    public String reviewIdGeneration(String maxReviewId){
        String reviewId;
        int count = 0;
        if(!util.isNullOrEmpty(maxReviewId)){
            count = Integer.parseInt(maxReviewId.replaceAll(REGEX_GET_NUMBERS, ""));
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
        return reviewId;
    }
}
