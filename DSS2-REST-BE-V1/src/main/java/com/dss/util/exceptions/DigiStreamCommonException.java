package com.dss.util.exceptions;

public class DigiStreamCommonException extends RuntimeException{

    public DigiStreamCommonException(String exceptionStr) {
        super(exceptionStr + "\n");
    }
}
