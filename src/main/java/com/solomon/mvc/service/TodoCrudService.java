package com.solomon.mvc.service;

import com.solomon.mvc.entity.ToDo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoCrudService {

    ToDo save(ToDo toDo);

    void delete(UUID todoId);

    ToDo update(ToDo toDo);

    default List<ToDo> findAll() {
        return findAll(LocalDate.now());
    }

    List<ToDo> findAll(LocalDate date);

    Optional<ToDo> findOne(UUID todoId);
}
