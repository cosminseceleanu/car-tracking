package com.cartracking.main.services.impl;

import com.cartracking.main.entities.Task;
import com.cartracking.main.entities.User;
import com.cartracking.main.repositories.TaskRepo;
import com.cartracking.main.services.TaskService;
import com.cartracking.main.services.UserService;
import com.cartracking.main.services.exceptions.NotFoundException;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepo taskRepo;
    private UserService userService;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo, UserService userService) {
        this.taskRepo = taskRepo;
        this.userService = userService;
    }

    @Transactional
    @Override
    public Task save(Task task, long employeeId) {
        setEmployee(task, employeeId);
        taskRepo.save(task);

        return task;
    }

    @Override
    public Page<Task> search(Predicate predicate, Pageable pageable) {
        return taskRepo.findAll(predicate, pageable);
    }

    @Override
    public Optional<Task> get(long id) {
        return Optional.ofNullable(taskRepo.findOne(id));
    }

    @Transactional
    @CacheEvict(cacheNames = "task_by_status_and_employee", allEntries = true)
    @Override
    public Task update(Task task, long employeeId) throws NotFoundException {
        setEmployee(task, employeeId);
        task = entityManager.merge(task);

        return taskRepo.save(task);
    }

    private void setEmployee(Task task, long employeeId) throws NotFoundException {
        User employee = userService.find(employeeId);
        if (employee == null) {
            throw new NotFoundException(String.format("No employee with id: %d found", employeeId));
        }
        task.setEmployee(employee);
    }
}
