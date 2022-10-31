package com.dss.util.exceptions;

public class DssCommonException extends RuntimeException{

    public DssCommonException(String exceptionStr) {
        super(exceptionStr + "\n");
    }
}
