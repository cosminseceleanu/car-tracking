package com.cartracking.rest.resources;

import org.springframework.hateoas.ResourceSupport;

public class TaskLogResource extends ResourceSupport {
    private long taskId;
    private double latitude;
    private double longitude;

    public TaskLogResource(long taskId) {
        this.taskId = taskId;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getTaskId() {
        return taskId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
