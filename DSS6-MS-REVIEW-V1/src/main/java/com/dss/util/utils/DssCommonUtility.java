/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */


package com.dss.util.utils;

import com.google.gson.*;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This is an DssCommonUtility Class.
 */

public class DssCommonUtility {

    /** Returns a String value of json object
     * @param e List<?> e
     * @return String json
     * @see #gsonToJsonString(List)
     */
    public String gsonToJsonString(List<?> e){
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocaleDateAdapter()).create();
        return gson.toJson(e);
    }

    static class LocaleDateAdapter implements JsonSerializer<LocalDate> {
        @Override
        public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_DATE));
        }
    }

    /** Returns true if the String value is empty, else, is false
     * @param param string value
     * @return boolean value
     * @see #isNullOrEmpty(String)
     */
    public boolean isNullOrEmpty(String param){
        return StringUtils.isEmpty(param);
    }
}
