package com.cartracking.main.entities.cassandra;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.util.Date;

@PrimaryKeyClass
public class TaskLogKey {

    @PrimaryKeyColumn(name = "task_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private long taskId;

    @PrimaryKeyColumn(name = "log_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private Date logTime;

    public TaskLogKey(long taskId, Date logTime) {
        this.taskId = taskId;
        this.logTime = logTime;
    }

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
}
