/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.client.util.exceptions;

/**
 * This is an Exception Class for AccountRegistrationNotFound
 */

public class AccountRegistrationNotFound extends RuntimeException {

    /**
     * Thrown this exception if the account is not found.
     * @param exceptionStr the detail message. The detail message is saved for later retrieval by the getMessage() method.
     */
    public AccountRegistrationNotFound(String exceptionStr) {
        super(exceptionStr + "\n");
    }
}
