package com.cartracking.rest.resources.asm;

import com.cartracking.main.entities.Task;
import com.cartracking.rest.controller.TaskController;
import com.cartracking.rest.resources.TaskResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

@Service
public class TaskResourceAsm extends ResourceAssemblerSupport<Task, TaskResource> {

    private UserResourceAsm userResourceAsm;

    @Autowired
    public TaskResourceAsm(UserResourceAsm userResourceAsm) {
        super(TaskController.class, TaskResource.class);
        this.userResourceAsm = userResourceAsm;
    }

    @Override
    public TaskResource toResource(Task task) {
        TaskResource taskResource = new TaskResource();
        taskResource.setRid(task.getId());
        taskResource.setLimitDate(task.getLimitDate());
        taskResource.setDestinationLongitude(task.getDestinationLongitude());
        taskResource.setDestinationLatitude(task.getDestinationLatitude());
        taskResource.setStartDate(task.getStartDate());
        taskResource.setStatus(task.getStatus());
        taskResource.setAddress(task.getAddress());
        taskResource.setEmployee(userResourceAsm.toResource(task.getEmployee()));

        return taskResource;
    }
}
