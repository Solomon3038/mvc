package com.solomon.mvc.service;

import com.solomon.mvc.entity.ToDo;
import com.solomon.mvc.repository.TodoRepository;
import com.solomon.mvc.repository.exception.NoResultToDoPersistenseException;
import com.solomon.mvc.repository.exception.StoreNotAvaliableTodoPersistenseException;
import com.solomon.mvc.service.exception.InternalServerErrorException;
import com.solomon.mvc.service.exception.TodoNotFoundException;
import com.solomon.mvc.service.postprocessor.DayOwWeekStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.time.LocalTime.MAX;
import static java.time.LocalTime.MIN;

@Service
@Transactional
@Slf4j
public class TodoCrudServiceImpl implements TodoCrudService {

    private final TodoRepository repository;

    private Collection<Predicate<ToDo>> todoFilters;

    private Collection<DayOwWeekStrategy<ToDo>> dayOwWeekStrategies;



    public TodoCrudServiceImpl(TodoRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }




    @Override
    public ToDo save(ToDo toDo) {
        return repository.save(toDo);
    }

    @Override
    public void delete(UUID todoId) {
        try {
            repository.deleteById(todoId);
        } catch (NoResultToDoPersistenseException ex) {
            throw new TodoNotFoundException(todoId);
        } catch (StoreNotAvaliableTodoPersistenseException ex) {
           throw new InternalServerErrorException("todo.servise.eror-by-delete-operation");
            //log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public ToDo update(ToDo toDo) {
        ToDo existsToDo = repository.findById(toDo.getId()).orElseThrow(RuntimeException::new);
        existsToDo.setText(toDo.getText());
        existsToDo.setPlannedTime(toDo.getPlannedTime());
        return repository.save(existsToDo);
    }

    @Override
    public List<ToDo> findAll(LocalDate date) {
        final List<ToDo> todoList = repository.findAllByDate(LocalDateTime.of(date, MIN), LocalDateTime.of(date, MAX));
        List<ToDo> result = todoList;
        if (!CollectionUtils.isEmpty(todoFilters)) {
            result = todoFilters.stream().flatMap(filter -> todoList.stream().filter(filter)).collect(Collectors.toList());
        }
        return result.stream().map(todo -> {
            return dayOwWeekStrategies
                    .stream()
                    .filter(strategy -> strategy.support(todo))
                    .findFirst()
                    .map(strategy -> strategy.process(todo)).orElse(todo);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<ToDo> findOne(UUID todoId) {
        return repository.findById(todoId);
    }

    @Autowired(required = false)
    public void setTodoFilters(Collection<Predicate<ToDo>> todoFilters) {
        this.todoFilters = todoFilters;
    }

    @Autowired
    public void setDayOwWeekStrategies(Collection<DayOwWeekStrategy<ToDo>> dayOwWeekStrategies) {
        this.dayOwWeekStrategies = dayOwWeekStrategies;
    }
}
