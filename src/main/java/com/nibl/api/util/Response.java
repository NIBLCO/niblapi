package com.nibl.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base class for all responses to the client
 */
public class Response {
    private HttpStatus status;
    private String message = "";

    public Response(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(HttpStatus status) {
        this.status = status;
    }

    @JsonIgnore
    public ResponseEntity<?> getResponseEntity() {
        return new ResponseEntity<>(this, status);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Response setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

}
