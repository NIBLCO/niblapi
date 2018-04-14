package com.nibl.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Use to return response with content to the client. If content is null Http Status will always be 404 Not Found
 *
 * param <T> Class of content field
 */
public class ContentResponse<T> extends Response {

    private T content;

    public ContentResponse(T content) {
        super(HttpStatus.OK);
        this.content = content;
    }

    public ContentResponse(String message, T content) {
        super(HttpStatus.OK, message);
        this.content = content;
    }

    public ContentResponse(HttpStatus status, String message, T content) {
        super(status, message);
        this.content = content;
    }

    @Override
    public ResponseEntity<?> getResponseEntity() {
        if (content == null) {
            setStatus(HttpStatus.NOT_FOUND);
            setMessage("Not found.");
        }
        return super.getResponseEntity();
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}