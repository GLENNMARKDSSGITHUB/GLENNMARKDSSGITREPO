package com.dss.util.exceptions;

public class AccountRegistrationNotFound extends RuntimeException {
    public AccountRegistrationNotFound(String exceptionStr) {
        super(exceptionStr + "\n");
    }
}
