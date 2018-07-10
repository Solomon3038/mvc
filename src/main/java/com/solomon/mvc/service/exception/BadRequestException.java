package com.solomon.mvc.service.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends MessagableException {
    public BadRequestException(String messageCode, Object... args) {
        super(messageCode, BAD_REQUEST, args);
    }
}
