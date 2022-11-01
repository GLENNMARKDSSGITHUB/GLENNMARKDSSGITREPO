package com.dss.util.exceptions;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

public class AccountExistingException extends RuntimeException {
    public AccountExistingException(String exceptionStr) {
        super(exceptionStr + "\n");
    }
}
