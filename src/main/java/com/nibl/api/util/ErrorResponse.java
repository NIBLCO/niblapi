package com.nibl.api.util;

import org.springframework.http.HttpStatus;

/**
 * Use to return error response messages to the client
 */
public class ErrorResponse extends Response {

    public ErrorResponse(HttpStatus status, String message) {
        super(status, message);
    }

}
