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
            count = Integer.parseInt(maxDssId.replaceAll(REGEX_GET_NUMBERS, ""));
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
        return dssId;
    }
}
