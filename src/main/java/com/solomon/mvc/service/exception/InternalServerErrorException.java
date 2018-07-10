package com.solomon.mvc.service.exception;

public class InternalServerErrorException  extends MessagableException{

    public InternalServerErrorException(String messageCode, Object... args) {
        super(messageCode, args);
    }
}
