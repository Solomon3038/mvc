package com.solomon.mvc.service;

import com.solomon.mvc.entity.ToDo;
import com.solomon.mvc.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static java.time.LocalTime.MAX;
import static java.time.LocalTime.MIN;

@Service
@Transactional
public class TodoCrudServiceImpl implements TodoCrudService {

    private final TodoRepository repository;

    public TodoCrudServiceImpl(TodoRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public ToDo save(ToDo toDo) {
        return repository.save(toDo);
    }

    @Override
    public void delete(UUID todoId) {
        repository.deleteById(todoId);
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
        return repository.findAllByDate(LocalDateTime.of(date, MIN), LocalDateTime.of(date, MAX));
    }

    @Override
    public Optional<ToDo> findOne(UUID todoId) {
        return repository.findById(todoId);
    }
}
