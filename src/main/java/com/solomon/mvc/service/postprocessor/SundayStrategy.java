package com.solomon.mvc.service.postprocessor;

import com.solomon.mvc.entity.ToDo;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
public class SundayStrategy implements DayOwWeekStrategy<ToDo> {
    @Override
    public boolean support(ToDo entity) {
        return entity.getPlannedTime().getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    @Override
    public ToDo process(ToDo entity) {
        return entity;
    }
}
