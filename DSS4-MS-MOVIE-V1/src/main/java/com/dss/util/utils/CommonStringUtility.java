package com.dss.util.utils;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

public class CommonStringUtility {

    /*=================================CUSTOM REGEX PATTERNS==================================*/
    public static final String REGEX_PATTERN_SPECIAL_CHAR_NUM = "[^a-zA-Z\\s+]";
    public static final String REGEX_PATTERN_EMAIL = ".+@.+\\..+";
    public static final String REGEX_PATTERN_PASSWORD = "^.*(?=.{8,})(?=.*\\d.*\\d)(?=.*[a-z].*[a-z])(?=.*[A-Z].*[A-Z])(?=.*[!@#$%^&*+=].*[!@#$%^&*+=]).*$";

    /*========================CUSTOMIZED ERROR MESSAGE FOR ADD REGISTRATION====================*/
    public static final String  ERR_CODE_001_REQ_FIRSTNAME = "First name is mandatory";
    public static final String  ERR_CODE_001_REQ_LASTNAME = "Last name is mandatory";
    public static final String  ERR_CODE_001_REQ_PASSWORD = "Password is mandatory";
    public static final String  ERR_CODE_001_REQ_STATUS = "Password is mandatory";
    public static final String  ERR_CODE_001_REQ_CELLPHONE_NO = "Cellphone number is mandatory";

    public static final String ERR_CODE_001_CELL_NO_TAKEN = "Cellphone number has already been taken.";
    public static final String ERR_CODE_001_EMAIL_TAKEN = "Email address has already been taken.";
    public static final String ERR_CODE_001_ALPHABET_ALLOWED = "Only alphabetical characters allowed.";
    public static final String ERR_CODE_001_INVALID_EMAIL = "Please enter a valid email.";
    public static final String ERR_CODE_001_PASSWORD_ALLOWED = "Password must have at least an uppercase and lowercase alphabet, a digit and a special character";

    /*========================CUSTOMIZED ERROR MESSAGE FOR DISPLAY REGISTRATION====================*/
    public static String ERR_CODE_002_NO_DISPLAY_RECORDS = "There are no records to display.";

    /*========================CUSTOMIZED ERROR MESSAGE FOR SEARCH AND UPDATE REGISTRATION====================*/
    public static String ERR_CODE_003_NO_RECORDS_FOUND = "There are no records retrieve in the database for email ID %s.";
    public static String ERR_CODE_004_ACCT_NOT_EXISTING = "Account not existing.";
    public static String ERR_CODE_005_PASSWORD_NOT_MATCH = "Your passwords do not match. Please try again.";

    /*========================CUSTOMIZED SUCCESS MESSAGE FOR REGISTRATION====================*/
    public static String SUCCESS_CODE_001_ADD_REG = "New account registration with the email ID %s has been created successfully.";
//    public static String SUCCESS_CODE_002_UPDATE_REG = "Account registration with the email ID %s has been updated successfully.";
    public static String SUCCESS_CODE_004_UPDATE_REG = "Password has been updated successfully.";
    public static String SUCCESS_CODE_003_SAME_PASSWORD = "Password must differ from old password.";
    public static String SUCCESS_CODE_003_DELETE_REG = "Account has been deleted successfully.";

    /*========================CUSTOMIZED SUCCESS MESSAGE FOR MOVIE CATALOGUE====================*/
    public static String SUCCESS_CODE_001_ADD_MOV = "New movie with the movie ID %s has been created successfully.";
    public static String SUCCESS_CODE_002_UPDATE_MOV = "The movie title %s has been updated successfully.";
    public static String SUCCESS_CODE_003_DELETE_MOV = "The movie title %s has been deleted successfully.";

    /*========================CUSTOMIZED ERROR MESSAGE FOR MOVIE CATALOGUE====================*/
    public static String ERR_CODE_001_MOVIE_EXIST = "Movie title %s already existing!";
    public static String ERR_CODE_002_MOVIE_NOT_EXIST = "Movie ID %s not existing!";

    /*========================CUSTOMIZED SUCCESS MESSAGE FOR ACTOR====================*/
    public static String SUCCESS_CODE_001_ADD_ACTOR = "Actor name %s has been added to the movie.";
    public static String SUCCESS_CODE_002_UPDATE_ACTOR = "Actor name %s has been updated successfully.";
    public static String SUCCESS_CODE_003_DELETE_ACTOR = "Actor name %s has been deleted successfully.";

    /*========================CUSTOMIZED ERROR MESSAGE FOR ACTOR====================*/
    public static String ERR_CODE_001_ACTOR_EXIST = "Actor name %s already existing!";

    /*========================CUSTOMIZED SUCCESS MESSAGE FOR REVIEWS====================*/
    public static String SUCCESS_CODE_001_ADD_REVIEW = "Your review has been added to the movie.";
    public static String SUCCESS_CODE_002_UPDATE_REVIEW = "Your review has been updated successfully.";

    /*======================== STR ID'S FOR MOVIE, ACTOR AND REVIEW ====================*/
    public static String US_ID_000X = "US000%s";
    public static String US_ID_00XX = "US00%s";
    public static String US_ID_0XXX = "US0%s";
    public static String US_ID_XXXX = "US%s";

    public static String DSS_ID_000X = "DSS000%s";
    public static String DSS_ID_00XX = "DSS00%s";
    public static String DSS_ID_0XXX = "DSS0%s";
    public static String DSS_ID_XXXX = "DSS%s";

    public static String ACT_ID_000X = "AC000%s";
    public static String ACT_ID_00XX = "AC00%s";
    public static String ACT_ID_0XXX = "AC0%s";
    public static String ACT_ID_XXXX = "AC%s";

    public static String REV_ID_000X = "RT000%s";
    public static String REV_ID_00XX = "RT00%s";
    public static String REV_ID_0XXX = "RT0%s";
    public static String REV_ID_XXXX = "RT%s";
}
