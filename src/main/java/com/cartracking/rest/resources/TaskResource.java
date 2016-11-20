package com.cartracking.rest.resources;

import com.cartracking.main.entities.Task;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

public class TaskResource extends ResourceSupport {
    private long rid;
    private Date limitDate;
    private Date startDate;
    private String status;
    private UserResource employee;
    private double destinationLatitude;
    private double destinationLongitude;
    private String address;

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserResource getEmployee() {
        return employee;
    }

    public void setEmployee(UserResource employee) {
        this.employee = employee;
    }

    public double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public Task toTask() {
        Task task = new Task();
        task.setId(rid);
        task.setAddress(address);
        task.setDestinationLatitude(destinationLatitude);
        task.setDestinationLongitude(destinationLongitude);
        if (status != null) {
            task.setStatus(status);
        }
        if (startDate != null) {
            task.setStartDate(startDate);
        }
        task.setLimitDate(limitDate);

        return task;
    }
}
