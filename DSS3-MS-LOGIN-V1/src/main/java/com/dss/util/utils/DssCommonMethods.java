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

    /** This method generates a new user ID which is based on the MAX userId in the DSS_USERS table.
     *  Returns a String value of maxUserId
     * @param maxUserId maxUserId
     * @return String value
     * @see #userIdGeneration(String)
     */
    public String userIdGeneration(String maxUserId){
        String userId;
        int count = 0;
        if(!util.isNullOrEmpty(maxUserId)){
            count = Integer.parseInt(maxUserId.replaceAll(REGEX_GET_NUMBERS, ""));
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
        return userId;
    }
}
