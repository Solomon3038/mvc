package com.solomon.mvc.service.postprocessor;

public interface DayOwWeekStrategy<T> {

    boolean support(T entity);

    T process(T entity);
}
