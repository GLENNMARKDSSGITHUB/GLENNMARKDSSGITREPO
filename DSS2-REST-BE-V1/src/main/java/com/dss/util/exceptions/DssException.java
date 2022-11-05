package com.dss.util.exceptions;

/**
 * This is an Exception Class for MovieNotFoundException
 */

public class DssException extends RuntimeException{

    /**
     * Thrown this exception if the movie is not found
     * @param exceptionStr the detail message. The detail message is saved for later retrieval by the getMessage() method.
     */

    public DssException(String exceptionStr) {
        super(exceptionStr + "\n");
    }
}
