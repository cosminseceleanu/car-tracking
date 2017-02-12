package com.cartracking.rest.resources.asm;

import com.cartracking.main.entities.cassandra.TaskLog;
import com.cartracking.rest.controller.TaskLogController;
import com.cartracking.rest.resources.TaskLogResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

@Service
public class TaskLogResourceAsm extends ResourceAssemblerSupport<TaskLog, TaskLogResource> {

    public TaskLogResourceAsm() {
        super(TaskLogController.class, TaskLogResource.class);
    }

    @Override
    public TaskLogResource toResource(TaskLog entity) {
        TaskLogResource resource = new TaskLogResource(entity.getTaskId());
        resource.setLongitude(entity.getLongitude());
        resource.setLatitude(entity.getLongitude());

        return resource;
    }
}
