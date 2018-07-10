package com.solomon.mvc.repository.exception;

public abstract class ToDoPersistenseException extends RuntimeException { //rootklass exception

    public ToDoPersistenseException() {
    }

    public ToDoPersistenseException(String message) {
        super(message);
    }

    public ToDoPersistenseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ToDoPersistenseException(Throwable cause) {
        super(cause);
    }
}
