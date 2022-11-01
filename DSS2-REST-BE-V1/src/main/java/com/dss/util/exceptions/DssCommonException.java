package com.dss.util.exceptions;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

public class DssCommonException extends RuntimeException{

    public DssCommonException(String exceptionStr) {
        super(exceptionStr + "\n");
    }
}
