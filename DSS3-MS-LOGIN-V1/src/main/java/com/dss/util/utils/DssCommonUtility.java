package com.dss.util.utils;

import com.google.gson.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

public class DssCommonUtility {
    private static final Logger logger = LoggerFactory.getLogger(DssCommonUtility.class);

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

    public boolean isNullOrEmpty(List<?> e) {
        return e.isEmpty();
    }

    public boolean isNullOrEmpty(String param){
        return StringUtils.isEmpty(param);
    }
}
