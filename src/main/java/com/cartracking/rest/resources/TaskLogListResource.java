package com.cartracking.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class TaskLogListResource extends ResourceSupport {
    private List<TaskLogResource> resourceList;

    public TaskLogListResource(List<TaskLogResource> resourceList) {
        this.resourceList = resourceList;
    }

    public List<TaskLogResource> getResourceList() {
        return resourceList;
    }
}
