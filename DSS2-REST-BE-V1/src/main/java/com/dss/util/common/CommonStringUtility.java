package com.dss.util.common;

public class CommonStringUtility {

    /*=================================CUSTOM REGEX PATTERNS==================================*/
    public static final String REGEX_PATTERN_SPECIAL_CHAR_NUM = "[^a-zA-Z\\s+]";
    public static final String REGEX_PATTERN_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String REGEX_PATTERN_PASSWORD = "^.*(?=.{15,})(?=.*\\d.*\\d)(?=.*[a-z].*[a-z])(?=.*[A-Z].*[A-Z])(?=.*[!@#$%^&*+=].*[!@#$%^&*+=]).*$";

    /*========================CUSTOMIZED ERROR MESSAGE FOR ADD REGISTRATION====================*/
    public static String ERR_CODE_001_CHECK_FIELDS = "Please check the fields.";
    public static String ERR_CODE_001_CELL_NO_TAKEN = "The cellphone number %s has already been taken.";
    public static String ERR_CODE_001_EMAIL_TAKEN = "The email address %s has already been taken.";
    public static String ERR_CODE_001_ALPHABET_ALLOWED = "Only alphabetical characters allowed.";
    public static String ERR_CODE_001_INVALID_EMAIL = "Please enter a valid email address.";
    public static String ERR_CODE_001_PASSWORD_ALLOWED = "Password must have at least an uppercase and lowercase alphabet, a digit and a special character!";

    /*========================CUSTOMIZED ERROR MESSAGE FOR DISPLAY REGISTRATION====================*/
    public static String ERR_CODE_002_NO_DISPLAY_RECORDS = "There are no records to display.";

    /*========================CUSTOMIZED ERROR MESSAGE FOR SEARCH AND UPDATE REGISTRATION====================*/
    public static String ERR_CODE_003_NO_RECORDS_FOUND = "There are no records retrieve in the database for email ID %s.";

    /*========================CUSTOMIZED SUCCESS MESSAGE FOR REGISTRATION====================*/
    public static String SUCCESS_CODE_001_ADD_REG = "New account registration with the email ID %s has been created successfully.";
    public static String SUCCESS_CODE_002_UPDATE_REG = "Account registration with the email ID %s has been updated successfully.";
    public static String SUCCESS_CODE_002_DELETE_REG = "Account registration with the email ID %s has been deleted successfully.";




//    public static String ADD_REG_SUCCESS_MSG = "New account with the registration ID %s has been created successfully.";
//    public static String ADD_REG_EXISTING_ACCT_MSG = "User ID %s already existing!";
//    public static String ENTER_VALID_REG_ID_MSG = "Please enter a valid registration ID!";
//    public static String DISP_NO_ACCT_REG_RECORD_MSG = "No GFlights registration records save in the database!";
//    public static String ACCT_REG_ID_NOT_FOUND_MSG = "Registration record with the User ID %s not found!";
//    public static String UPDATE_DELETE_REG_SUCCESS_MSG = "Registration record with the User ID %s has been %s successfully.";
//    public static String INCORRECT_PASSWORD = "Password is incorrect!!";

}
