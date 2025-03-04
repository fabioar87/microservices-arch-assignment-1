package com.atus.api.exceptions;

public class Error {
    private static final long serialVersionUID = 1l;
    private String code;
    private String reqMethod;
    private String errorMessage;
    private Integer status;

    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String method) {
        this.reqMethod = method;
    }

    public void setErrorCode(String errorCode) {
        this.code = errorCode;
    }

    public String getErrorCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
