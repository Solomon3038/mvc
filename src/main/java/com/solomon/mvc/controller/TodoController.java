package com.solomon.mvc.controller;

import com.solomon.mvc.entity.ToDo;
import com.solomon.mvc.service.TodoCrudService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoCrudService service;

    public TodoController(TodoCrudService service) {
        this.service = Objects.requireNonNull(service);
    }

    @GetMapping
    public List<ToDo> toDoList(@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        if (date == null) {
            return service.findAll();
        }
        return service.findAll(date);
    }

    @GetMapping("/{id}")
    public ToDo toDo(@PathVariable("id")UUID id) {
        return service.findOne(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ToDo save(@RequestBody ToDo toDo) {
        return service.save(toDo);
    }

    @PutMapping
    public ToDo update(@RequestBody ToDo toDo) {
        return service.update(toDo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void notFoundHandler(RuntimeException ex) {

    }
}
