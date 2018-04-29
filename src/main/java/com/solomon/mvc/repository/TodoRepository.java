package com.solomon.mvc.repository;

import com.solomon.mvc.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<ToDo, UUID> {

    @Query("select td from ToDo td where td.plannedTime between :from and :to")
    List<ToDo> findAllByDate(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
