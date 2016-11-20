package com.cartracking.main.services.impl;

import com.cartracking.main.entities.Task;
import com.cartracking.main.entities.User;
import com.cartracking.main.repositories.TaskRepo;
import com.cartracking.main.services.TaskService;
import com.cartracking.main.services.UserService;
import com.cartracking.main.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
    public Task save(Task task, long employeeId) {
        setEmployee(task, employeeId);
        taskRepo.save(task);

        return task;
    }

    @Override
    public Page<Task> getAll(long userId, Pageable pageable) {
        return taskRepo.findByEmployeeAdminId(userId, pageable);
    }

    @Override
    public Optional<Task> get(long id) {
        return Optional.ofNullable(taskRepo.findOne(id));
    }

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
