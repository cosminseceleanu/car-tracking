package com.cartracking.rest.resources;

import org.springframework.hateoas.PagedResources;

import java.util.List;

public class TaskListResource extends PagedResources {
    private List<TaskResource> taskResourceList;

    public TaskListResource(List<TaskResource> taskResourceList) {
        this.taskResourceList = taskResourceList;
    }

    public List<TaskResource> getTaskResourceList() {
        return taskResourceList;
    }
}
