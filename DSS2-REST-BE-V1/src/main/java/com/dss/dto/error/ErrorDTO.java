package com.dss.dto.error;

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
