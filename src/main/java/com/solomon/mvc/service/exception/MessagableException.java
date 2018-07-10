package com.solomon.mvc.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
public class MessagableException extends RuntimeException {

    private final String messageCode;

    private final Object[] args;

    private final HttpStatus status;


    public MessagableException(String messageCode, Object... args) {
        this(messageCode, INTERNAL_SERVER_ERROR,args);

    }
    public MessagableException(String messageCode,HttpStatus status, Object... args) {
        this.messageCode = messageCode;
        this.args = args;
        this.status = status;
    }






}
