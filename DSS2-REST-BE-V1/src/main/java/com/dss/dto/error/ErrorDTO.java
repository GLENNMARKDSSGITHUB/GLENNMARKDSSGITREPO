/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.dto.error;

/**
 * This class is a Data Transfer Object for ErrorDTO
 */

public class ErrorDTO {

    private String errorMessage;
    private String statusCode;
    private String exceptionMessage;

    public ErrorDTO() {
    }

    public ErrorDTO(String errorMessage, String statusCode, String exceptionMessage) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
        this.exceptionMessage = exceptionMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
