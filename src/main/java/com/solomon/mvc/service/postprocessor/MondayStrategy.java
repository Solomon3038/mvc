package com.solomon.mvc.service.postprocessor;

import com.solomon.mvc.entity.ToDo;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
public class MondayStrategy implements DayOwWeekStrategy<ToDo> {

    @Override
    public boolean support(ToDo toDo) {
        return toDo.getPlannedTime().getDayOfWeek() == DayOfWeek.MONDAY;
    }

    @Override
    public ToDo process(ToDo entity) {
        entity.setPlannedTime(entity.getPlannedTime().plusHours(1L));
        return entity;
    }
}
