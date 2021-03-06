package com.cartracking.rest.controller;

import com.cartracking.main.entities.Task;
import com.cartracking.main.services.TaskService;
import com.cartracking.rest.exceptions.NotFoundException;
import com.cartracking.rest.resources.TaskResource;
import com.cartracking.rest.resources.asm.TaskResourceAsm;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskResourceAsm taskResourceAsm;

    @Autowired
    private PagedResourcesAssembler<Task> pagedResourcesAssembler;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/employees/{employeeId}/tasks", method = RequestMethod.POST)
    public ResponseEntity<?> create(@PathVariable long employeeId, @RequestBody TaskResource taskResource) {
        taskService.save(taskResource.toTask(), employeeId);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tasks/search", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<TaskResource>> search(@QuerydslPredicate(root = Task.class) Predicate predicate, Pageable pageable) {
        Page<Task> tasks = taskService.search(predicate, pageable);
        PagedResources<TaskResource> resources = pagedResourcesAssembler.toResource(tasks, taskResourceAsm);

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public ResponseEntity<TaskResource> get(@PathVariable long id) {
        Optional<Task> taskOptional = taskService.get(id);
        Task task = taskOptional.orElseThrow(() ->
                new NotFoundException(String.format("No taskOptional with %d found", id)));

        return new ResponseEntity<>(taskResourceAsm.toResource(task), HttpStatus.OK);
    }

    @RequestMapping(value = "/employees/{employeeId}/tasks/{taskId}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable long employeeId,
                                    @PathVariable long taskId,
                                    @RequestBody TaskResource resource) {

        Optional<Task> taskOptional = taskService.get(taskId);
        taskOptional.orElseThrow(() -> new NotFoundException(String.format("No task with %d found", taskId)));
        try {
            taskService.update(resource.toTask(), employeeId);
        } catch (com.cartracking.main.services.exceptions.NotFoundException e) {
            throw new NotFoundException(e);
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
