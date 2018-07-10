package com.solomon.mvc.service.filter;

import com.solomon.mvc.entity.ToDo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@Service
public class SampleTodoFilter implements Predicate<ToDo> {
    @Override
    public boolean test(ToDo toDo) {
        return toDo.getPlannedTime().isAfter(LocalDateTime.now().minusDays(3L));
    }
}
