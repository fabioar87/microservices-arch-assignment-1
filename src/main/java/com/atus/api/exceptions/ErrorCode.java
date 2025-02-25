package com.atus.api.exceptions;

public enum ErrorCode {
    SYSTEM_ERROR("ERROR-01", "System is unable to complete the request");

    private String errorCode;
    private String errorMessage;

    ErrorCode(final String code, final String errorMessage ) {
        this.errorCode = code;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
