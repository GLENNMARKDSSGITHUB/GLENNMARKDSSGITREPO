package com.dss.util.utils;

import com.google.gson.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

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

    //public static final String PASSWORD_REGEX_PATTERN = "^.*(?=.{15,})(?=.*\\d.*\\d)(?=.*[a-z].*[a-z])(?=.*[A-Z].*[A-Z])(?=.*[!@#$%^&*+=].*[!@#$%^&*+=]).*$";
    public boolean patternMatchesByEmail(String emailPass, String regex){
        return Pattern.compile(regex)
                .matcher(emailPass)
                .matches();
    }

//    public static final String SPECIAL_CHAR_NUM_REGEX_PATTERN = "[^a-zA-Z\\s+]";
    public static final String EMAIL_REGEX_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public boolean patternMatches(String name, String regex){
        String nameStr = name.replaceAll("\\s", "");
        return Pattern.compile(regex , Pattern.CASE_INSENSITIVE)
                .matcher(nameStr).find();
    }

    public boolean isNullOrEmpty(List<?> e) {
        return e.isEmpty();
    }

    public boolean isNullOrEmpty(String param){
        return StringUtils.isEmpty(param);
    }
}
