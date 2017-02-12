package com.cartracking.main.entities.cassandra;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

@Table(value = TaskLog.TABLE_NAME)
public class TaskLog {

    public final static String TABLE_NAME = "task_logs";

    @PrimaryKeyColumn(name = "task_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private long taskId;

    @PrimaryKeyColumn(name = "log_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private Date logTime;


    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private double speed;

    @Column
    private double altitude;

    @Column(value = "employee_id")
    private long employeeId;

    @Column(value = "car_id")
    private long carId;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }
}
