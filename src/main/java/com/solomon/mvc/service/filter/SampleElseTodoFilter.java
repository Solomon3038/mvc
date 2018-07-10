package com.solomon.mvc.service.filter;

import com.solomon.mvc.entity.ToDo;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

import static java.time.DayOfWeek.SUNDAY;

@Service
public class SampleElseTodoFilter implements Predicate<ToDo> {
    
    @Override
    public boolean test(ToDo toDo) {
        return toDo.getPlannedTime().getDayOfWeek() != SUNDAY;
    }
}
