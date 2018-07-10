package com.solomon.mvc.controller;

import com.solomon.mvc.entity.ToDo;
import com.solomon.mvc.service.TodoCrudService;
import com.solomon.mvc.service.exception.BadRequestException;
import com.solomon.mvc.service.exception.InternalServerErrorException;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoCrudService service;

    private final MessageSource messageSource;

    public TodoController(TodoCrudService service, MessageSource messageSource) {
        this.service = Objects.requireNonNull(service);
        this.messageSource = Objects.requireNonNull(messageSource);
    }

    @GetMapping
    public List<ToDo> toDoList(@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        if (date == null) {
            return service.findAll();
        }
        return service.findAll(date);
    }

    @GetMapping("/{id}")
    public ToDo toDo(@PathVariable("id") UUID id) {
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

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handleBadRequest(BadRequestException ex, HttpServletRequest req) {
       return  messageSource.getMessage(ex.getMessageCode(), ex.getArgs(), req.getLocale());

    }
    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handleBadRequest(InternalServerErrorException ex, HttpServletRequest req) {
        return  messageSource.getMessage(ex.getMessageCode(), ex.getArgs(), req.getLocale());

    }
}
