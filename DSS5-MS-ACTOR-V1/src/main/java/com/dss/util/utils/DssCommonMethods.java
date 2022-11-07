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
            count = Integer.parseInt(maxActorId.replaceAll(REGEX_GET_NUMBERS, ""));
        }

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
        return actorId;
    }
}
