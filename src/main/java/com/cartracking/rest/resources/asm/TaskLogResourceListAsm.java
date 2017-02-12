package com.cartracking.rest.resources.asm;

import com.cartracking.main.entities.cassandra.TaskLog;
import com.cartracking.rest.controller.TaskLogController;
import com.cartracking.rest.resources.TaskLogListResource;
import com.cartracking.rest.resources.TaskLogResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLogResourceListAsm extends ResourceAssemblerSupport<List<TaskLog>, TaskLogListResource> {
    private TaskLogResourceAsm resourceAsm;

    @Autowired
    public TaskLogResourceListAsm(TaskLogResourceAsm taskLogResourceAsm) {
        super(TaskLogController.class, TaskLogListResource.class);
        this.resourceAsm = taskLogResourceAsm;
    }

    @Override
    public TaskLogListResource toResource(List<TaskLog> entity) {
        List<TaskLogResource> resourceList = resourceAsm.toResources(entity);

        return new TaskLogListResource(resourceList);
    }
}
