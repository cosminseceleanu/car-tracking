package com.cartracking.main.services;

import com.cartracking.main.entities.Task;
import com.cartracking.main.entities.cassandra.TaskLog;
import com.cartracking.main.rabbitmq.message.TaskLogMessage;

import java.util.List;

public interface TaskLogService {
    void add(TaskLogMessage taskLogMessage, Task task);
    List<TaskLog> get(long taskId);
}
