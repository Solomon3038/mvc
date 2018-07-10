package com.solomon.mvc.service.exception;

import java.util.UUID;

public class TodoNotFoundException extends BadRequestException {

    public TodoNotFoundException(UUID todoId) {
        super("todo.service.not-found", todoId);
    }
}
