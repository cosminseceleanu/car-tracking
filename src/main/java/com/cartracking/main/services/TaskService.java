package com.cartracking.main.services;

import com.cartracking.main.entities.Task;
import com.cartracking.main.services.exceptions.NotFoundException;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface TaskService {
    Task save(Task task, long employeeId);

    Page<Task> search(Predicate predicate, Pageable pageable);

    Optional<Task> get(long id);

    Task update(Task task, long employeeId) throws NotFoundException;
}
