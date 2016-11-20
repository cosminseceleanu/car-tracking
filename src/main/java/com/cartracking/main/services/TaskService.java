package com.cartracking.main.services;

import com.cartracking.main.entities.Task;
import com.cartracking.main.services.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface TaskService {
    Task save(Task task, long employeeId);

    Page<Task> getAll(long userId, Pageable pageable);

    Optional<Task> get(long id);

    Task update(Task task, long employeeId) throws NotFoundException;
}
