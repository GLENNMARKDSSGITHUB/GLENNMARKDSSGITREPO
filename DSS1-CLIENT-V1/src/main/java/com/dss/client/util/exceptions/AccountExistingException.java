/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.client.util.exceptions;

/**
 * This is an Exception Class for AccountExistingException
 */

public class AccountExistingException extends RuntimeException {

    /**
     * Thrown this exception if the account is already existing.
     * @param exceptionStr the detail message. The detail message is saved for later retrieval by the getMessage() method.
     */
    public AccountExistingException(String exceptionStr) {
        super(exceptionStr + "\n");
    }
}
