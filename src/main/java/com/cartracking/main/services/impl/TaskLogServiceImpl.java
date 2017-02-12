package com.cartracking.main.services.impl;

import com.cartracking.main.entities.Task;
import com.cartracking.main.entities.cassandra.TaskLog;
import com.cartracking.main.rabbitmq.message.TaskLogMessage;
import com.cartracking.main.repositories.TaskLogRepo;
import com.cartracking.main.services.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLogServiceImpl implements TaskLogService {
    private TaskLogRepo taskLogRepo;

    @Autowired
    public TaskLogServiceImpl(TaskLogRepo taskLogRepo) {
        this.taskLogRepo = taskLogRepo;
    }

    @Override
    public void add(TaskLogMessage taskLogMessage, Task task) {
        taskLogRepo.add(adaptToEntity(taskLogMessage, task));
    }

    @Override
    public List<TaskLog> get(long taskId) {
        return taskLogRepo.find(taskId);
    }

    private TaskLog adaptToEntity(TaskLogMessage taskLogMessage, Task task) {
        TaskLog taskLog = new TaskLog();
        taskLog.setTaskId(task.getId());
        taskLog.setLogTime(taskLogMessage.getDateTime());
        taskLog.setLatitude(taskLogMessage.getLatitude());
        taskLog.setLongitude(taskLogMessage.getLongitude());
        taskLog.setSpeed(taskLogMessage.getSpeed());
        taskLog.setAltitude(taskLogMessage.getAltitude());
        taskLog.setEmployeeId(taskLogMessage.getEmployeeId());
        taskLog.setCarId(task.getEmployee().getCar().getId());

        return taskLog;
    }
}
