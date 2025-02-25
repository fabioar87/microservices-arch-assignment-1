package com.atus.api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class RestApiErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(RestApiErrorHandler.class);
    private final MessageSource messageSource;

    @Autowired
    public RestApiErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(HttpServletRequest request, Exception ex, Locale locale) {
        ex.printStackTrace();
        Error error = new Error();
        error.setErrorCode(ErrorCode.SYSTEM_ERROR.getErrorCode());
        error.setErrorMessage(ErrorCode.SYSTEM_ERROR.getErrorMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setReqMethod(request.getMethod());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
