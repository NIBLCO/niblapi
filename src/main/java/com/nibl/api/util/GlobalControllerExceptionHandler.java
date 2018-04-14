package com.nibl.api.util;

import javax.ws.rs.ClientErrorException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()).getResponseEntity();
    }

    @ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<?> handleExceptionBadRequest(RuntimeException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()).getResponseEntity();
    }

    @ExceptionHandler(ClientErrorException.class)
    public ResponseEntity<?> handleExceptionClientError(ClientErrorException e) {
        return new ErrorResponse(HttpStatus.valueOf(e.getResponse().getStatus()), e.getMessage()).getResponseEntity();
    }

}
