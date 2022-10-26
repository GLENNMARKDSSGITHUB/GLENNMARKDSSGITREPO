package com.dss.util.exceptions;

public class AccountExistingException extends RuntimeException {
    public AccountExistingException(String exceptionStr) {
        super(exceptionStr + "\n");
    }
}
